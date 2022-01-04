from DataProcessing import*
from DataLoading import*
import numpy as np
import os

base_directory=os.path.abspath(os.path.dirname(os.getcwd()))
print(base_directory)
result=load_baby_data(base_directory)

result_plot=plot_concentration(result["ID"],
            result["glucose concentration"],
            result["glucose timestamp"],
            result["skin concentration"],
            result["skin timestamp"],
            result["event"],
            result["event timestamp"],
            base_directory)