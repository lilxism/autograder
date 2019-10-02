#Activity0
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

#a
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

#b
gambler=PairOfDice()
repeat=24
test=10000
count=0 #count of double-six appear
for t in range(test):
	for n in range(repeat):
		gambler.roll()
		if gambler.getDice1()==6 and gambler.getDice2()==6:
			count=count+1
			break
			
print("Activity 0 (b) test case:")
print("Percentage: "+ str(count/test))
print("")

#Activity1 : randomly select and display 13 cards from a deck of cards
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
		#Randomly select a rank and a suit.
		ranks = ['2', '3', '4', '5', '6', '7', '8', '9',
		"10", "jack", "queen", "king", "ace"]
		self._rank = random.choice(ranks)
		self._suit = random.choice(["spades", "hearts", "clubs", "diamonds"]) 
    
	def __str__(self):
		'''returns string representation of card'''
		return(self._rank + " of " + self._suit) 

#for sorting the 13 cards by suit
def sortSecond(val):
	v=val.__str__().split(" ")
	return v[2]
	

select=13
p=PlayingCard() 
collect=[] #to store chosen cards
 
for s in range(13):
	p.selectAtRandom()
	#since cards are selected from a deck, make sure there is no repeat card
	while p.__str__() in collect:
		p.selectAtRandom()
	#store card into list
	collect.append(p.__str__())
	
#sort card based on suits
collect.sort(key=sortSecond,reverse=True)

print("Activity 1 test case:")
#print cards
for c in collect:
	print(c)

print("")
	
#Activity2: Rabbits multiply
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
		#n = not pregnant
		#p = pregnant
		#r = rest days
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

#store total rabbits(does and bucks) for each trial in list
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
		
			#check conditions for does
			if r.getGender()=="female":
			
				#rabbit status is n and age is over 100 days old, rabbit got pregnant
				if r.getStatus()=='n' and r.getAge()>=100:	
					r.pregnant()
					
				#rabbit is pregnant
				elif r.getStatus()=='p': 
					#if pregnant days countdown to 0, D-DAY, give birth!
					if r.getDays()==0: 
						#assign number of babies (does or bucks)
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
					
						#set status to rest and set days to 7
						r.rest()
					#countdown on pregnant days
					else:
						r.setDays(r.getDays()-1)
				
				#rabbit is in rest 
				elif r.getStatus()=='r':
					#rabbit done resting, set to not pregnant
					if r.getDays()==1: 
						r.setStatus('n')
					#countdown on rest days
					else:
						r.setDays(r.getDays()-1)
				else:
					pass
	
	#calculation for total rabbits (does and bucks) for each trial
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

#print average and std	
print("Average number of rabbits: "+str(statistics.mean(allRabbits))+" with std of "
+str(round(statistics.stdev(allRabbits),2)))
print("Average number of female rabbits: "+str(statistics.mean(allDoes))+" with std of "
+str(round(statistics.stdev(allDoes),2)))
print("Average number of male rabbits: "+str(statistics.mean(allBucks))+" with std of "
+str(round(statistics.stdev(allBucks),2)))
	