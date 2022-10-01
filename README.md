
# First_Second_Longest_Compounded_word-

- The problem statement can be found in the Exercise_Fresher_Word_Problem.pdf file.

## Data Structure 
- To solve this problem i use Trie data Structure.It can be insert faster and search the string than hash tables and TreeMap or any other data Structure. 
  and the searching and insert porocess will be going on alphabet to alphabet.


## Approach To solve the problem

- Create An ArrayList to store the word one by one from the selected file , using BufferedReader. -
- Create a Node and under Node we define Links[26], flag, and ch. 
- After that build a Trie 
- Initialize the Longest And Second_Longest variable 
- Getting word one by one from ArrayList and call the method check_if_compounded(word) 
- Under this method each chararacter from the word check is it present or not in the trie. 
- Now if the word is compounded then check if it is longer than longest word if yes the swap







