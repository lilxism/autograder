''' 
This version take user input from a text file
'''

#explore the use of sets and dictionaries

def main():
	# read input file for user input
	f = open("python_labs/Lab04/file.txt", "r")
	text = f.read()
	inputList = text.split(",")
	
	#Activity 0
	l1 = [1,1,2,3,4,4,5,5,]     
	l2 = [1,3,5, 6]     
	print(removeDuplicates(l1))     
	print(findItemsInBoth(l1,l2))     
	print(findItemsInEither(l1,l2))     
	print()	

	#Activity 1
	#a. Rewrite isYoungest
	#pres = input("Who was the youngest U.S. president? ")
	pres = inputList[0]
	print("Who was the youngest U.S. president? ")
	print(">",pres)
	pres = pres.upper()
	isYoungest(pres)
	print()	

	#b. Rewrite determineRank 
	#y = int(input("Enter your year: ")) 
	y = int(inputList[1])
	print("Your year: ")
	print(">",y)
	print(determineRank(y)) 
	print()	

	#Activity2
	scrabble_score('monkeys')
	print()	

	topHitters={"Gehrig":{"atBats":8061,"hits":2721},
			"Ruth":{"atBats":8399,"hits":2873},
			"Williams":{"atBats":7706,"hits":2654}}
	batting_averages(topHitters)
	
def removeDuplicates(list1):
	return set(list1)

def findItemsInBoth(list1, list2):
	return set(list1)&set(list2)

def findItemsInEither(list1, list2):
	return set(list1)|set(list2)

def isYoungest(pres):
	rightAns={"THEODORE ROOSEVELT", "TEDDY ROOSEVELT", "TR"}
	possibleAns={"JFK", "JOHN KENNEDY", "JOHN F. KENNEDY"}

	if pres in rightAns:
		print("Correct.  He became president at the age of 42")
		print("when President McKinley was assassinated.")

	elif pres in possibleAns:
		print("Incorrect.  He became president at age of 43. However,")
		print("he was the youngest person elected president.")

	else:
		print("Nope!")
 

def determineRank(years):  
	if years == 1:   
		return "Freshman"  
	elif years == 2:   
		return "Sophomore"  
	elif years == 3:   
		return "Junior"  
	else:   
		return "Senior"  

def determineRank(years):
	year={1: 'Freshman', 2:'Sophomore', 3:'Junior', 4:'Senior'}
	if years in year:
		return year.get(years)
	else:
		return "senior"

def scrabble_score(word):
	dict={}
	dict[1]=['E','A','O','N','R','T','L','S','U']
	dict[2]=['D','G']
	dict[3]=['B','C','M','P']
	dict[4]=['F','H','V','W','Y']
	dict[5]=['K']
	dict[8]=['J','X']
	dict[10]=['Q','Z']
	
	word = word.upper()
	
	total=0
	for w in word:
		for c in dict:
			if w in dict[c]:
				total+=c
	
	print(total)
	return total	

def batting_averages(tophitters):
	for h in tophitters:
		print(h+" "+str(tophitters[h].get('hits')/tophitters[h].get('atBats')))
	
	
main()

"""
Question
After implementing the activity above, please answer the following questions:
1. Is there an advantage to using a dictionary over using conditionals?
Yes.
2. For this type of problem, is it better/easier to use conditionals or a dictionary?
dictionary.
It is better/easier to use conditionals.
"""
"""
Question
After implementing the activity above, please answer the following question:
1. For this type of problem, it is better/easier to use conditionals or a dictionary?
It is better/easier to use dictionary.
"""
"""
Question
After implementing the activity above, please answer the following question:
1. What was the most difficult part of this activity?
The most difficult part of this activity is to get the values of a value.
"""
	
