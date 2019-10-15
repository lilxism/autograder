#Test fail at activity0(b)
#Test fail at activity1 (not sorted)
#No comments on number of questions
#No comments in general

import random
import statistics
class PairOfDice:
	def _init_(self):
		self._dice1=0
		self._dice2=0
	
	def getDice1(self):
		return self._dice1
	
	def getDice2(self):
		return self._dice2
	
	def roll(self):
		self._dice1=random.choice(range(1,7))
		self._dice2=random.choice(range(1,7))
	
	def sum(self):
		return self._dice1+self._dice2

p1=PairOfDice()
p2=PairOfDice()

p1.roll()
p2.roll()

print("Activity 0 (a) test case:")
print("Player 1: "+str(p1.sum()))
print("Player 2: "+str(p2.sum()))
if p1.sum()>p2.sum():
	print("Player 1 wins.")
elif p1.sum()<p2.sum():
	print("Player 2 wins.")
else:
	print("Tie.")

print("")

gambler=PairOfDice()
repeat=24
test=10000
count=0 
for t in range(test):
	for n in range(repeat):
		gambler.roll()
		if gambler.getDice1()==6 and gambler.getDice2()==6:
			count=count+1
			
			
print("Activity 0 (b) test case:")
print("Percentage: "+ str(count/test))
print("")

class PlayingCard:
	def __init__(self, rank="queen", suit="hearts"):
		'''Creates a representation of a card - rank and suit'''
		self._rank = rank
		self._suit = suit 
	
	def setRank(self, rank):
		'''sets the rank of card'''
		self._rank = rank 
     
	def setSuit(self, suit):
		'''sets the suit of the card'''
		self._suit = suit 
     
	def getRank(self):
		'''returns the rank of the card'''
		return self._rank 
     
	def getSuit(self):
		'''returns the suit of the card'''
		return self._suit
	
	def selectAtRandom(self):
		'''sets the rank and suit to a random card'''
		ranks = ['2', '3', '4', '5', '6', '7', '8', '9',
		"10", "jack", "queen", "king", "ace"]
		self._rank = random.choice(ranks)
		self._suit = random.choice(["spades", "hearts", "clubs", "diamonds"]) 
    
	def __str__(self):
		'''returns string representation of card'''
		return(self._rank + " of " + self._suit) 

select=13
p=PlayingCard() 
collect=[] 
 
for s in range(13):
	p.selectAtRandom()
	while p.__str__() in collect:
		p.selectAtRandom()
	collect.append(p.__str__())

print("Activity 1 test case:")
for c in collect:
	print(c)

print("")
	
class rabbit:
	def __init__(self, gender, age=0,status='n',days=-1):
		'''Creates a representation of a rabbit - age and gender'''
		self._age = age
		self._gender = gender
		self._status = status
		self._days=days
	
	def setAge(self, age):
		'''sets the age of rabbit'''
		self._age = age
     
	def setGender(self, gender):
		'''sets the gender of the rabbit'''
		self._gender = gender 
	
	def setStatus(self, status):
		'''sets the status of the rabbit'''
		self._status = status
	
	def setDays(self, days):
		'''sets the rest days for the rabbit'''
		self._days = days
	
	def getAge(self):
		'''returns the age of the rabbit'''
		return self._age 
		
	def getGender(self):
		'''returns the gender of the rabbit'''
		return self._gender 
	
	def getStatus(self):
		'''returns the age of the rabbit'''
		return self._status
	
	def getDays(self):
		'''returns the rest days of the rabbit'''
		return self._days
		
	def pregnant(self):
		"""set status of rabbit to pregnant and set gestational period"""
		self._status='p'
		self._days=random.randint(28,33)
	
	def rest(self):
		"""set status of rabbit to rest for 7 days""" 
		self._status='r'
		self._days=7
	
	def __str__(self):
		'''returns string representation of rabbit'''
		return("age:"+str(self._age) + " gender:" + self._gender) 
		
time=365 #1 year
trial=10

allRabbits=[]
allDoes=[]
allBucks=[]
print("Activity 2 test case:")
print("Starting with 3 females rabbits and 1 male rabbit all at 0 days of age, after 1 year:")
for tr in range(trial):
	rabbits=[]
	rabbits.append(rabbit("female"))
	rabbits.append(rabbit("female"))
	rabbits.append(rabbit("female"))
	rabbits.append(rabbit("male"))
	
	for t in range(time):
		for r in rabbits:
			r.setAge(r.getAge()+1)
			if r.getGender()=="female":
				if r.getStatus()=='n' and r.getAge()>=100:	
					r.pregnant()
					
				elif r.getStatus()=='p': 
					if r.getDays()==0: 
						babies=random.randint(3,9)
						does=0
						bucks=0
						if babies%2!=0 :
							does=int((babies/2)+1)
						else:
							does=int(babies/2)
						
						bucks=int(babies/2)
			
						for f in range(does):
							rabbits.append(rabbit("female"))
						for m in range(bucks):
							rabbits.append(rabbit("male"))
					
						r.rest()
					else:
						r.setDays(r.getDays()-1)
				
				elif r.getStatus()=='r':
					if r.getDays()==1: 
						r.setStatus('n')
					else:
						r.setDays(r.getDays()-1)
				else:
					pass
	
	
	totalRabbits=len(rabbits)
	totalDoes=0
	totalBucks=0
	for r in rabbits:
		if r.getGender()=='female':
			totalDoes=totalDoes+1
		else:
			totalBucks=totalBucks+1
			
	print("Trial " + str(tr+1)+": "+str(totalRabbits)+" rabbits, "+str(totalDoes)+" females and "
	+str(totalBucks)+" males")
	
	
	allRabbits.append(totalRabbits)
	allDoes.append(totalDoes)
	allBucks.append(totalBucks)


print("Average number of rabbits: "+str(statistics.mean(allRabbits))+" with std of "
+str(round(statistics.stdev(allRabbits),2)))
print("Average number of female rabbits: "+str(statistics.mean(allDoes))+" with std of "
+str(round(statistics.stdev(allDoes),2)))
print("Average number of male rabbits: "+str(statistics.mean(allBucks))+" with std of "
+str(round(statistics.stdev(allBucks),2)))
	