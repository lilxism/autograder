#explore the use of sets and dictionaries

#Activity 0
def main():
	l1 = [1,1,2,3,4,4,5,5,]     
	l2 = [1,3,5, 6]     
	print(removeDuplicates(l1))     
	print(findItemsInBoth(l1,l2))     
	print(findItemsInEither(l1,l2))      

def removeDuplicates(list1):
	return set(list1)

def findItemsInBoth(list1, list2):
	return set(list1)&set(list2)

def findItemsInEither(list1, list2):
	return set(list1)|set(list2)
main()