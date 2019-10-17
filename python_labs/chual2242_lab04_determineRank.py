main()   
 
#b. Rewrite determineRank 
def main():     
	y = int(input("Enter your year: "))     
	print(determineRank(y)) 

	
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