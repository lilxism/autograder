# Activity 0
def isAlpha(L):
	if (len(L) == 1):
		return True
	elif(L[0] > L[1]):
		return False
	else:
		L.pop(0)
		return isAlpha(L)

words1 = ["cat", 'dog', "fish", 'giraffe', 'horse']
words2 = ['cat', 'duck', 'dog','hiipo']

print(isAlpha(words1))
print(isAlpha(words2))

# Activity 1
def writeNums(n):
	if (n == 1):
		print(n, end ="")
	else:
		writeNums(n - 1)
		print(", ", n, end ="")

writeNums(5)
print()

# Activity 2
def repeat(s, n):
	if (n == 0):
		return ""
	return s + repeat(s, n-1)

print(repeat("repeat", 4))

# Activity 3
def indexOfFirst(s1, s2):
	if (len(s1) == 0 or len(s2) > len(s1)):
		return -1
	elif s2 == s1[:len(s2)]:
		return 0;
	else:
		n = indexOfFirst(s1[1:], s2)
		if (n == -1):
			return -1
		else:
			return 1+n	
print(indexOfFirst("Barak Obama", "ma"))

# Activity 4
def multiplyEvens(n):
	if (n == 1):
		return 2
	else:
		return (2*n)*(multiplyEvens(n-1))


print(multiplyEvens(4))