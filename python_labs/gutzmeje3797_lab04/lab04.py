# https://www.onlinegdb.com/online_python_compiler
# 
#

# '''
# Activity 0
def main():
	list1 = [1,1,2,3,4,4,5,5,]
	list2 = [1,3,5, 6]
	print(removeDuplicates(list1))
	print()
	print(findItemsInBoth(list1,list2))
	print()
	print(findItemsInEither(list1, list2))

def removeDuplicates(list1):
	set1 = set(list1)
	return list(set1)

def findItemsInBoth(list1, list2):
	set1 = set(list1)
	set2 = set(list2)
	set3 = set1.intersection(set2)
	return list(set3)
	
def findItemsInEither(list1, list2):
	set1 = set(list1)
	set2 = set(list2)
	set1.update(set2)
	return list(set1)
	
main() 
# '''

# '''
# Activity 1a
def main():
	pres = input("Who was the youngest U.S. president? ")
	pres = pres.upper()
	isYoungest(pres)

def tr_message():
	print("Correct. He became president at the age of 42")
	print("when President McKinley was assasinated.")

def jfk_message():
	print("Incorrect. He became president at age of 43. However,")
	print("he was the youngest person elected president.")
	
def isYoungest(pres):
	tr_dict = {
		"THEODORE ROOSEVELT": tr_message,
		"TEDDY ROOSEVELT": tr_message,
		"TR": tr_message,
		"JFK": jfk_message,
		"JOHN KENNEDY": jfk_message,
		"JOHN F. KENNEDY": jfk_message
	}
	function = tr_dict.get(pres)
	try:
		function()
	except:
		print("Nope!")

main() 
# '''

# '''
# Activity 1b
def main():
	y = int(input("Enter your year: "))
	print(determineRank(y))
	
def determineRank(years):
	rank_dict = {
		1: "Freshman",
		2: "Sophomore",
		3: "Junior"
	}
	
	return rank_dict.get(years, "Senior")

main()
# '''

# '''
# Activity 2
def main():
	word = input("Enter a word: ")
	word = word.upper()
	print(scrabbel_score(word))
	
def scrabbel_score(word):
	score_dict = {
		"E": 1,
		"A": 1,
		"I": 1,
		"O": 1,
		"N": 1,
		"R": 1,
		"T": 1,
		"L": 1,
		"S": 1,
		"U": 1,
		"D": 2,
		"G": 2,
		"B": 3,
		"C": 3,
		"M": 3,
		"P": 3,
		"F": 4,
		"H": 4,
		"V": 4,
		"W": 4,
		"Y": 4,
		"K": 5,
		"J": 8,
		"X": 8,
		"Q": 10,
		"Z": 10
	}
	
	score = 0
	for letter in word:
		score = score + score_dict.get(letter)
		
	return score

main()
# '''

#'''
# Activity 3
def main():
	topHitters = {"Gehrig":{"atBats":8061, "hits":2721},
					"Ruth":{"atBats":8399, "hits":2873},
					"Williams":{"atBats":7706, "hits":2654}}
	
	average(topHitters)
 
def average(topHitters):	
	for name, stats_dict in topHitters.items():
		average = stats_dict.get("hits") / stats_dict.get("atBats")
		print("{0:10} {1:.3f}" .format(name, average))
	
main()
#'''















