#Activity 1
#a. Rewrite isYoungest
def main():
	pres = input("Who was the youngest U.S. president? ")
	pres = pres.upper()
	isYoungest(pres)


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

main()
