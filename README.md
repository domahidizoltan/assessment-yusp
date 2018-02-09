# Closest Point Pairs application

author: Zoltán Domahidi  
date: January 20, 2018  


## Exercise

Your task is to create a Java program which reads a text file where each line contains the coordinates 
of a multidimensional point, and then looks for the closest pair of points in the file. If the program has found the 
closest pair of points, it should output the line numbers of the two closest points.

The text file contains one point per line. The coordinate values are separated by a tabulator character. 
The coordinate values are not necessarily integers. In case of a floating point coordinate value the decimal separator 
is a period. 

### Notes

After analyzing the sample input and output files I made the following assumptions
 * the application should handle points of any dimension
 * in a given input file every point must have the same number of dimensions using floating points representation
 * the output file must contain the coordinates of the point in Integer format
 * there are 3 exceptions where I found decimals in the expected output files (sample_output_4_4.txt, sample_output_100_100.txt).  
 I could not figure out why do they differ, 
 so I considered this as an error, and my solution will output the rounded Integer coordinates in these cases as well 


## The application

The application could be run from an IDE by running the ```ClosestPairApplication.main()``` method or using command line 
by invoking the Gradle wrapper command ```./gradlew bootrun```  

The application will read up all the files from ```src/main/resources/data/input``` folder and will output the results to 
```src/main/resources/data/output``` folder. The expected results are stored in ```src/test/resources/data/output``` folder.  

The actual results and the expected results can be compared by any diff tool, or by running the ```compare_results.sh``` script,
written in bash. 

The application was built by using Test-Driven Development, so most of the classes and features are covered with tests.  

The application hides implementations behind interfaces, so this will enable to extend the functionalities. 
(i.e. it is easy to read/write data from outher sources, use other methods to measure distance, do other operations with the data)  

The main functionalities are in the following classes:
* ```com.yusp.closestpair.distance.EuclideanDistanceCalculator```: calculates the distance of two given points from any dimensional space 
* ```com.yusp.closestpair.service.ClosestPairPointsAnalyzer```: analyzes the input data of points and return the pair of two closest points  

The other classes are responsible for reading/writing values from files, modulatiry of the application, configuring and running the application.  

### Notes

The application process data on a single thread at the moment. If we want to process huge amount of data, 
it could be changed to process data in parallel way. At this moment the algorithm is using Java8 Streams what is 
very easy to use parallel, but the underlying data structures are not thread-safe, so the results will be erroneous.

