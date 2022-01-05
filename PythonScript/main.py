from DataProcessing import*
from DataLoading import*
import numpy as np
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
import pyCompare
import os
import sys

#Find the base directory where the project is working
base_directory=sys.argv[1]
#According to the instruction do drift removement
result=load_baby_data(base_directory)
if (result["drift"]=="linear regression"):
    result["glucose concentration"]=drift_removement_by_linear_fitting(result["glucose concentration"])
    result["skin current"]=drift_removement_by_linear_fitting(result["skin current"])
    result["skin concentration"]=drift_removement_by_linear_fitting(result["skin concentration"])
elif (result["drift"]=="differentiation"):
    result["glucose concentration"]=drift_removement_by_1st_order_differentiation(result["glucose concentration"])
    result["skin current"]=drift_removement_by_1st_order_differentiation(result["skin current"])
    result["skin concentration"]=drift_removement_by_1st_order_differentiation(result["skin concentration"])
#According to the instruction do noise removement
if (result["noise"]=="moving average"):
    result["glucose concentration"]=noise_removement_by_moving_average(result["glucose concentration"])
    result["skin current"]=noise_removement_by_moving_average(result["skin current"])
    result["skin concentration"]=noise_removement_by_moving_average(result["skin concentration"])
elif (result["noise"]=="savitzky golay"):
    result["glucose concentration"]=noise_removement_by_savitzky_golay(result["glucose concentration"])
    result["skin current"]=noise_removement_by_savitzky_golay(result["skin current"])
    result["skin concentration"]=noise_removement_by_savitzky_golay(result["skin concentration"])
#Generate and save plots
plot_concentration(result["ID"],
            result["glucose concentration"],
            result["glucose timestamp"],
            result["skin concentration"],
            result["skin timestamp"],
            result["event"],
            result["event timestamp"],
            base_directory)