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

def main():
	scrabble_score('monkeys')

#Activity2
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

main()

"""
Question
After implementing the activity above, please answer the following question:
1. For this type of problem, it is better/easier to use conditionals or a dictionary?
It is better/easier to use dictionary.
"""
def main():
	topHitters={"Gehrig":{"atBats":8061,"hits":2721},
			"Ruth":{"atBats":8399,"hits":2873},
			"Williams":{"atBats":7706,"hits":2654}}
	batting_averages(topHitters)

def batting_averages(tophitters):
	for h in tophitters:
		print(h+" "+str(tophitters[h].get('hits')/tophitters[h].get('atBats')))
	
main()

"""
Question
After implementing the activity above, please answer the following question:
1. What was the most difficult part of this activity?
The most difficult part of this activity is to get the values of a value.
"""