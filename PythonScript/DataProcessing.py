import numpy as np
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
import pyCompare

def drift_removement_by_linear_fitting(data):
    '''
        Detrend the time series(data) by fitting the linear regression model
    and return the result which has been substracted the linear part

        This function only works for glucose concentration, skin current/concentration
    and assume that the input data is in order

    input:
        data: List<float>, a list of float data that represents the concentration
            or the current of a baby
    
    return:
        result: List<float>, a list of float data that has been modified
    '''
    #Use float32 for a more accurate result
    X=[i for i in range(len(data))]
    X=np.reshape(X,(len(X),1)).astype(np.float32)
    Y=np.array(data).astype(np.float32)
    model=LinearRegression()
    model.fit(X,Y)
    trend=model.predict(X)
    result=[Y[i]-trend[i] for i in range(len(data))]
    return result

def drift_removement_by_1st_order_differentiation(data):
    '''
        Detrend the time series(data) by 1st order differencing and return
    the result as a list of float data

        The 1st order differencing is calculatig the difference between the
    current point and the previous point. For the 1st point which has no previous
    point, just take the original value as result
    
        The ith result is:
        Y(i)=y(i)-y(i-1)

    input:
        data: List<float>, a list of float data that represents the concentration
            or the current of a baby
    
    return:
        result: List<float>, a list of float data that has been modified
    '''
    result=[]
    template=np.array(data).astype(np.float32)
    for index in range(len(template)):
        if index==0:
            result.append(template[index])
        else:
            result.append(template[index]-template[index-1])
    return result

def noise_removement_by_moving_average(data, window_size=5):
    '''
        Apply the simple moving average filter to the time series(data) and
    return the result as a list of float

        The simple moving average filter is basically taking the weighted
    sum of M points into account at a certain time moment. For the 1st and
    2nd point which don't have enought previous points for filtering, the 
    zero-padding is applied.

        By default, the window size is set to 5 so the ith result is:
        Y(i)=0.2*[y(i-4)+y(i-3)+y(i-2)+y(i-1)+y(i)]
    
    input:
        data: List<float>, a list of float data that represents the concentration
            or the current of a baby
        window_size: int, the length(M) of the moving average filter which could 
            affect the strength of the smoothing

    return:
        result: List<float>, a list of float data that has been filtered
    '''
    #Caculate the result for each element and append it into a new list
    result=[]
    for index in range(len(data)):
        #If there is no enough point in a window, use zero padding
        if index<window_size-1:
            result.append(1/window_size*np.sum(data[:index+1]).astype(np.float32))
        else:
            result.append(1/window_size*np.sum(data[index-(window_size-1):index+1]).astype(np.float32))
    return result
        
def noise_removement_by_savitzky_golay(data, window_size=5):
    '''
        Apply the savitzky golay filter to the time series(data) and
    return the result as a list of float

        The savitzky golay filter uses a set of M convolution coefficients
    to take the unevenly distributed wighted sum at a certain time momnet.
    For the points which don't have enought points for filtering, the zero-
    padding is applied

        By default, the window size is set to 5 so the ith result is:
        Y(i)=1/35*[-3y(i-2)+12y(i-1)+17y(i)+12y(i+1)-3y(i+2)]

        Before version 2, the savitzky golay filter only supplies window size of 5
    
    input:
        data: List<float>, a list of float data that represents the concentration
            or the current of a baby
        window_size: int, the length(M) of the savitzky golay filter which could 
            affect the strength of the smoothing

    return:
        result: List<float>, a list of float data that has been filtered
    '''
    result=[]
    #May use a savitzky golay coefficient generator in version 2 for other window size
    window=[-3/35,12/35,17/35,12/35,-3/35]
    #Pad two 0s at the beginning and the ending of the data for calculation
    new_data=[0,0]+data+[0,0]
    #Do convolution action along the new data starting from the non-zero value
    for index in range(2,len(new_data)-2):
        result.append(np.dot(window,new_data[index-2:index+3]).astype(np.float32))
    return result

def plot_concentration(babyID, glucose_concentration, glucose_timestamp, skin_concentration, skin_timestmap, event, event_timestamp, directory):
    '''
        (1) Plot the glucose concentration and skin glucose concentration with respect to time as well as
    the event at certain time points separately and save the plot as a png image

        (2) Plot the glucose concentration with respect to the skin glucose concentration and save
    the plot as a png image (only if they share the same dimension)

        (3) Plot the Bland-Altman plot only if they have the same dimension

        As the baby is continuously monitored by the skin glucose concentration sensor,
    assume that the range of the skin timestamp is larger than the glucose/event timestmap and 
    all glucose/event timestamps are within this time period

        If the glucose concentration and skin glucose concentration don't have the same 
    dimension, assume the skin glucose concentration will have a higher dimension

        The skin glucose sensor takes sample at a certain regular time interval so assume
    that the time difference between adjacent skin timestamp is the same and it is 1min for now

        Assume all timestamps have the same date

        This function will be improved if real data could be given from the professor

        By default, the plot images could be saved under: Base\DataBase\Plots
   
    input:
        babyID: String, the unique ID of the baby who is monitored
        date: String, date in the format: yyyy/MM/dd
        glucose_concentration: List<float>, a list of float data that represents the concentration
            or the current of a baby
        glucose_timestamp: List<String>, a list of string that represents the time in format: HH:mm:ss
            for each concentration
        event: List<String>, a list of string that contains the information about the event
            which might influence the concentration measurement at certain time points
        event_timestamp: List<String>, a list of string that represents the time in format: HH:mm:ss
            for each evnet
        directory: String, the base working directory of the user
    
    return:
        plot: png image, saved under Base\DataBase\Plots
    '''
    #Ignore difference in secods and generate time index for glucose/event timestamp
    skin_time_index=[i for i in range(len(skin_timestmap))]
    glucose_time_index=[]
    event_time_index=[]
    #Set the shared time scale which could be used for both plots
    date=skin_timestmap[0][:10]
    time_scale=[time[11:16] for time in skin_timestmap]
    #Set the search limit to improve efficiency
    limit=0
    for time in glucose_timestamp:
        for index in range(limit,len(skin_timestmap)):
            if (time[:-3] in skin_timestmap[index]):
                glucose_time_index.append(index)
                limit=index
    limit=0
    for time in event_timestamp:
        for index in range(limit,len(skin_timestmap)):
            if (time[:-3] in skin_timestmap[index]):
                event_time_index.append(index)
                limit=index
    
    
    if glucose_time_index[0] > skin_time_index[0]:
        glucose_time_index.insert(0,skin_time_index[0])
    
    #Set bar property: line height is the height to display the event at certain time
    line_height=0.5*np.amin(glucose_concentration)
    event_height=[line_height for i in range(len(event_time_index))]
    
    #Save glucose concentration with timestamp as well as event
    figure, axis=plt.subplots(figsize=(9,5), dpi=300)
    axis.vlines(x=event_time_index,ymin=0,ymax=event_height,color="firebrick",alpha=0.7,linewidth=1)  
    axis.scatter(x=event_time_index,y=event_height,color="firebrick",alpha=0.7)    
    axis.plot(glucose_time_index,glucose_concentration,color="black",label="Glucose Concentration")
    axis.set_title("Blood Glucose Concentration over time on "+date)
    axis.set_ylabel("Concentration (mmol/L)")
    axis.set_xlabel("Time (hour)")
    axis.set_xticks(skin_time_index)
    axis.set_xticklabels(time_scale,fontsize="large",rotation=60)
    axis.set_ylim(0,np.amax(skin_concentration)+5)
    for i in range(len(event_time_index)):
        axis.text(event_time_index[i],event_height[i]+1,event[i],fontsize="large",horizontalalignment="center")
    
    figure.savefig(directory+"/DataBase/Plots/GlucoseTime.png")
    
    #Do the same for the skin concentration
    line_height=0.5*np.amin(skin_concentration)
    event_height=[line_height for i in range(len(event_time_index))]
    #Save skin concentration with timestamp as well as event
    figure, axis=plt.subplots(figsize=(9,5), dpi=300)
    
    axis.vlines(x=event_time_index,ymin=0,ymax=event_height,color="firebrick",alpha=0.7,linewidth=1)
    axis.scatter(x=event_time_index,y=event_height,color="firebrick",alpha=0.7)
    axis.plot(skin_time_index,skin_concentration,color="red",label="Skin Glucose Concentration")
    axis.set_title("Skin Glucose Concentration over time on "+date)
    axis.set_ylabel("Concentration (mmol/L)")
    axis.set_xlabel("Time (hour)")
    axis.set_xticks(skin_time_index)
    axis.set_xticklabels(time_scale,fontsize="large",rotation=60)
    axis.set_ylim(0,np.amax(skin_concentration)+5)
    for i in range(len(event_time_index)):
        axis.text(event_time_index[i],event_height[i]+1,event[i],fontsize="large",horizontalalignment="center")
    figure.savefig(directory+"/DataBase/Plots/SkinTime.png")
    #If not the same dimension, certain skin glucose values are chosen and compared with glucose concentration
    new_skin_concentration=[skin_concentration[time] for time in glucose_time_index]
    #Fit a linear regression model to calculate the gradient and intercept
    model=LinearRegression()
    model.fit(np.array(glucose_concentration).reshape((len(glucose_concentration),1)),np.array(new_skin_concentration).reshape((len(new_skin_concentration),1)))
    score=model.score(np.array(glucose_concentration).reshape((len(glucose_concentration),1)),np.array(new_skin_concentration).reshape((len(new_skin_concentration),1)))
    gradient=np.float64(model.coef_[0])
    intercept=np.float64(model.intercept_[0])
    #Plot the correlation graph which contains the scatter plot and the ideal plot
    start=np.amin(skin_concentration).astype(np.int8)
    end=np.amax(skin_concentration).astype(np.int8)
    ideal=[i for i in range(start,end+1)]
    figure,axis=plt.subplots(figsize=(9,5),dpi=300)
    axis.scatter(glucose_concentration,new_skin_concentration,alpha=0.5,marker="x",color="black")
    axis.plot(ideal,ideal,linestyle="--",color="red")
    axis.set_title("Skin Glucose over Blood Glucose Concentration")
    plt.figtext(.65, .2, "Score: "+"{:.2f}".format(score)+" "+"\nGradient: "+"{:.2f}".format(gradient)+" "+"\nIntercept: "+"{:.2f}".format(intercept))
    axis.set_ylabel("Skin Glucose Concentration (mmol/L)")
    axis.set_xlabel("Blood Glucose Concentration (mmol/L)")
    figure.savefig(directory+"/DataBase/Plots/correlation.png")
    #Finally plot the bland-altman graph between 2 concentrations
    pyCompare.blandAltman(glucose_concentration,new_skin_concentration,title="Bland Altman Plot",
                        figureSize=(9,5),dpi=300,savePath=directory+"/DataBase/Plots/BlandAltman.png")

