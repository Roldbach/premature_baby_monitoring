#For people who is working on drift removement, you might find this helpful: https://machinelearningmastery.com/time-series-trends-in-python/
#Remember to remove these when you finish coding
import numpy as np
import matplotlib.pyplot as plt
def drift_removement_by_linear_fitting(data):
    '''
        Detrend the time series(data) by fitting the linear regression model
    and return the result which has been substracted the linear part

        #Remove this part if you fnish coding and completely understand the whole prcoess
        
        General Procedure:
        (1) Fit a linear regression model using the input data
        (2) Use the coefficients from the linear model to predict the linear part
        (3) Substract the linear part from the input data and return the result

        For example, the input data is [7,8,9]
        (1) Fit a linear regression model and get x=t+1
        (2) Predict the linear part: substitute t=[0,1,2] (as only given 3 points)
            into the x=t+1 to get x=[1,2,3]
        (3) Substract the linear part to finally get: [6,6,6]
        
        #Remove end

    input:
        data: List<float>, a list of float data that represents the concentration
            or the current of a baby
    
    return:
        result: List<float>, a list of float data that has been modified
    '''

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
    
    input:
        data: List<float>, a list of float data that represents the concentration
            or the current of a baby
        window_size: int, the length(M) of the savitzky golay filter which could 
            affect the strength of the smoothing

    return:
        result: List<float>, a list of float data that has been filtered
    '''

def plot_glucose_concentration(babyID, date, glucose_concentration, glucose_timestamp, event, event_timestamp):
    '''
        Plot the glucose concentration with respect to time as well as
    the event at certain time points and save the plot as a png image

        Assume the glucose concentration data, timestamp and event are in order

        By default, the plot image could be saved under: Base\Plot
    
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
    
    return:
        plot: png image, saved under Base\Plot
    '''
    #Set list of index for glucose concentration and event
    glucose_index=[i for i in range(len(glucose_concentration))]
    event_index=[]
    #Set bar property
    line_height=0.5*np.amin(glucose_concentration)

    figure, axis=plt.subplot(figsize=(8,5), dpi=80)
    
    

    axis.vlines(x=event_x,ymin=0,ymax=y,color="firebrick",alpha=0.7,linewidth=2)
    axis.scatter(x=event_x,y=y,color="firebrick",alpha=0.7)
    axis.plot(x,concentration,color="black",label="Glucose Concentration")
    axis.plot(x,skin,color="red",label="Skin Concentration")

    axis.set_title("Test Concentration + Event")
    axis.set_ylabel("Concentration")
    axis.set_xticks(x)
    axis.set_ylim(25,70)

    for i in range(6):
        axis.text(event_x[i],y[i]+1,event[i],fontsize="large",horizontalalignment="center")
    plt.show()

'''
x=[0,1,2,3,4,5,6,7,8,9,10]
y=[30,30,30,30,30,30]
concentration=[50,59,53,55,51,49,56,52,57,51,60]
skin=[49,62,63,55,54,59,51,58,50,51,53]
event_x=[1,4,6,8,9,11]
event=["test 1","test 2","test 3","test 4","test 5"," test 6"]

figure,axis=plt.subplots(figsize=(8,5), dpi=80)
axis.vlines(x=event_x,ymin=0,ymax=y,color="firebrick",alpha=0.7,linewidth=2)
axis.scatter(x=event_x,y=y,color="firebrick",alpha=0.7)
axis.plot(x,concentration,color="black",label="Glucose Concentration")
axis.plot(x,skin,color="red",label="Skin Concentration")

axis.set_title("Test Concentration + Event")
axis.set_ylabel("Concentration")
axis.set_xticks(x)
axis.set_ylim(25,70)

for i in range(6):
    axis.text(event_x[i],y[i]+1,event[i],fontsize="large",horizontalalignment="center")
plt.show()
'''