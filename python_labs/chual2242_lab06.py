#Lab 6 : recursion

def main():
	#Activity0 test
	words1=["cat","dog","fish","giraffe","horse"]
	words2=["cat","duck","dog","hippo"]
	print(isAlpha(words1))
	print(isAlpha(words2))
	print("")
	
	#Activity1 test
	writeNums(9)
	print("")
	writeNums(5)
	print("")
	writeNums(1)
	print("")
	writeNums(0)
	print("")
	
	#Activity2 test
	repeat('hello',3)
	print("")
	repeat('okay',1)
	print("")
	repeat('cats',4)
	print("\n")
	
	#Activity3 test
	print(indexOfFirst("Barack Obama","bam"))
	print(indexOfFirst("spaghetti","no"))
	print(indexOfFirst("noodle","noo"))
	print(indexOfFirst("no","noodle"))
	print("")
	
	#Activity4 test
	print(multiplyEvens(4))
	print(multiplyEvens(5))
	print(multiplyEvens(9))
	
#Activity 0: Convert from iterative to recursive
#check if the list given is in alphabetically order.
def isAlpha(L):
	if len(L)==1:
		return True
	elif L[0] > L[1] :
		return False
	else:
		L.pop(0)
		return(isAlpha(L))
"""
Question:
1. What is recursion? How does it differ from a standard iterative method?
Recursion is when a statement in a function calls itself repeatedly. The difference is that 
recursion is a process always applied to a function while iteration is applied to the set
of instructions which we want to get repeatedly executed.
2. What are base case and recursive cases? Why does a recursive method need to have both?
Base case returns a value without making any recursive calls while recursive cases are where 
the program calls itself. A recursive method need to have both base case and recursive cases
because base case tells when the recursion should stop and recursive cases allow recursions 
to occur.
"""

#Activity 1: Practice
#print numbers on console starting with 1 and separated by commas
def writeNums(n):
	if n==1:
		print(n,end="")
	elif n>1:
		writeNums(n-1)
		print(",",end=str(n))
	else:
		print("n is less than 1")
		
#Activity 2: Practice
#return string concatenated n times
def repeat(s,n):
	if n!=0:
		print(s,end='')
		repeat(s,n-1)
		
#Activity 3: practice
#check if the s2 is in s1 and return the starting index of the first occurrence.
#print(s1.find(s2))
def indexOfFirst(s1,s2):
	if (len(s1) == 0 or len(s2)>len(s1)):
		return -1
	elif s2==s1[:len(s2)]:
		return 0
	else:
		n=indexOfFirst(s1[1:],s2)
		if(n==-1):
			return -1
		else:
			return 1+n

#Activity4: practice
#multiply first n even numbers
def multiplyEvens(n):
	if n<=0:
		return 1
	else:
		return 2*n*int(multiplyEvens(n-1))
		
main()



	