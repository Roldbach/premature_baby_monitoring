import os
def load_baby_data(directory):
    '''
        Load baby data and instructions and return a dictionary containing all information

        The value and timestamp are stored separately as the value
    requires further data processing

        By defaut, the baby data could be found under: Base\DataBase\Baby
                   the instruction could be found under: Base\DataBase

    input:
        directory: String, the base working directory of the user
    
    return:
        result: Dictionary, key=name of information, value=object
                ID: babyID, string
                drift: name of the drift removal technique, String
                noise: name of the noise removal technique, String
                glucose concentration: glucose concentration, List<float>
                glucose timestamp: glucose concentration timestamp, List<String>
                skin current: skin current, List<float>
                skin concentration: skin concentration, List<float>
                skin timestamp: skin current/concentration timestamp, List<String>
                evnet: event information, List<String>
                event timestamp: event timestmap, List<String>
    '''
    result={}
    #Load instruction file to get user's choice for drift/noise removal and baby ID
    with open(directory+"\DataBase\instruction.txt","r") as file:
        content=file.readlines()
        for line in content:
            if ("drift:" in line):
                result["drift"]=line[6:].strip("\n")
            elif ("noise:" in line):
                result["noise"]=line[6:].strip("\n")
            else:
                result["ID"]=line[3:].strip("\n")
    #Initiate all lists with matched name
    name_list=["glucose concentration","glucose timestamp","skin current","skin concentration","skin timestamp","event","event timestamp"]
    for name in name_list:
        result[name]=[]
    #Use the given baby ID to load the baby data as the file is saved using the baby ID
    with open(directory+"\DataBase\Baby\\"+result["ID"]+".txt","r") as file:
        content=file.readlines()
        for line in content:
            if ("gc:" in line):
                current_line=line[3:].strip("\n").split(",")
                result["glucose timestamp"].append(current_line[0])
                result["glucose concentration"].append(float(current_line[1]))
            elif ("sa:" in line):
                current_line=line[3:].strip("\n").split(",")
                result["skin timestamp"].append(current_line[0])
                result["skin current"].append(float(current_line[1]))
            elif ("sc:" in line):
                current_line=line[3:].strip("\n").split(",")
                #No need to append timestamp as skin current and skin concentration shares exactly the same timestamp
                result["skin concentration"].append(float(current_line[1]))
            elif ("ev:" in line):
                current_line=line[3:].strip("\n").split(",")
                result["event timestamp"].append(current_line[0])
                result["event"].append(current_line[1])
    return result

    