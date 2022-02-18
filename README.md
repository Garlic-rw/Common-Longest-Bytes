# Common-Longest-Bytes

Given a large number of binary files, write a program that finds the
longest strand of bytes that is identical between two or more files

Use the test set attached (files sample.*)

The program should display:
- the length of the strand
- the file names where the largest strand appears
- the offset where the strand appears in each file

## Running this program
- It takes around 10 minutes to find the longest common strand in 10 different binary files, so 
- To use this program with other binary files, please put the sample binary files under the /samples directory. 

## Algorithm
- I converted binary files into string in hexadecimal representation, so that every consecutive two characters represent the information in one byte. Then using dynamic programming, find the longest common substring that appears in at least two files. When comparing, account two characters as one unit and compare them.

## Future Improvement
- Dynamic programming doesn't have the most optimized time complexity and space complexity. A way to optimize runtime would be using general suffix tree to retrieve the longest common substring. 
- - To find the longest common substring, we can find the the deepest node marked with at least two strings, and the sequence from the root to that node will be the longest common substring we are trying to find here.
- - Remaining problem: If we don't know how many files we will be dealing with, we need to find a way to separate an unknown amount of different strings.
