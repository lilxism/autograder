# Activity 0: Baby Names I
def activity0():
	import glob
	
	years = range(1880, 2019)
	data = count_occurrences_in_years(years, 'Heather')
	plot_national_occurrences(data, years, 'Heather')
	
	years = range(1880, 2019)
	data = count_occurrences_in_years(years, 'Heather')
	plot_national_percentages(data, years, 'Heather')
	
	print("Finished Activity 0")

def count_occurrences_in_file(path, target):
	#Return the count for the target name and the total count for the year
	
	name_count = 0;
	total_count = 0;
	with open(path) as f:
		content = f.readlines()
		content = [x.strip() for x in content]
		for line in content:
			arr = line.split(",")
			total_count += int(arr[2])
			if (arr[0].lower() == target.lower()):
				name_count += int(arr[2])
			
	
	return [name_count, total_count]
	
def count_occurrences_in_years(years, target):
	#Return a list of occurrences for the range of years
	print("Gathering data...")
	pathName0 = "names\yob"
	pathName2 = ".txt"
	occurrences = []
	for year in years:
		pathName1 = str(year)
		path = pathName0 + pathName1 + pathName2
		result = count_occurrences_in_file(path, target)
		occurrences.append(result)
		
	return occurrences
	
def plot_national_occurrences(data, years, target):
	#Plot the number of occurrences in the given range of years 
	
	import matplotlib.pyplot as plt
	
	counts = []
	for list in data:
		counts.append(list[0])
	
	plt.plot(years, counts, linewidth=4)
	plt.xlabel('Years', fontsize=20)
	plt.ylabel('Number of Occurrences', fontsize=20)
	plt.title('Number of Occurrences for '+target, fontsize=24)
	plt.show()

def plot_national_percentages(data, years, target):
	#Plot the percentages of occurrences in the given range of years
	from matplotlib import pyplot as plt

	percentages = []
	for list in data:
		percentages.append(list[0] / list[1])
	
	plt.plot(years, percentages, linewidth=4)
	plt.xlabel('Years', fontsize=20)
	plt.ylabel('Percentage of Occurrences', fontsize=20)
	plt.title('Percentage of Occurrences for '+target, fontsize=24)
	plt.show()
	
activity0();

# Activity 1: Baby Names II
def activity1():
	import glob

	data = filter_occurrences_in_file('namesByState/KS.TXT', 'Heather')
	plot_state_occurrences(data, 'Heather' )

	totals = count_yearly_totals('namesByState/KS.TXT')
	plot_state_percentages(data, totals, 'Heather')

	print("Finished Activity 1")

def filter_occurrences_in_file(path, target):
	''' Return a dict with the number of occurrences for the target name in each year. '''
	
	dict = {}
	with open(path) as f:
		content = f.readlines()
		content = [x.strip() for x in content]
		for line in content:
			arr = line.split(",")
			
			if (arr[3].lower() == target.lower()):
				dict[arr[2]] = arr[4]				
				
	return dict

def count_yearly_totals(path):
	#Return a dict with the total number of occurrences in each year. ''' 
	dict = {}
	with open(path) as f:
		content = f.readlines()
		content = [x.strip() for x in content]
		for line in content:
			arr = line.split(",")
			occ = dict.get(arr[2])
			if occ is None:
				dict[arr[2]] = arr[4]
			else:
				occ = int(occ)
				occ += int(arr[4])
				dict.update({arr[2]: occ})
				
	return dict

def plot_state_occurrences(data, target):
	#Plot the number of occurrences for the state data
	import matplotlib.pyplot as plt
		
	counts = []
	years = []
	for year in range(1880, 2019):
		years.append(year)
		counts.append(int(data.get(str(year), 0)))	
	
	plt.plot(years, counts, linewidth=4)
	plt.xlabel('Years', fontsize=20)
	plt.ylabel('Number of Occurrences', fontsize=20)
	plt.title('Number of Occurrences for '+target, fontsize=24)
	plt.show()
	
def plot_state_percentages(data, totals, target):
	#Plot the percentages of occurrences for the state data
	import matplotlib.pyplot as plt
	
	percentages = []
	years = []
	for year in range(1880, 2019):
		years.append(year)
		count = int(data.get(str(year), 0))
		total = int(totals.get(str(year), 0))
		if (total != 0):
			percentages.append(count / total)
		else:
			percentages.append(0.0)

	plt.plot(years, percentages, linewidth=4)
	plt.xlabel('Years', fontsize=20)
	plt.ylabel('Percentage of Occurrences', fontsize=20)
	plt.title('Percentage of Occurrences for '+target, fontsize=24)
	plt.show()
	
	

activity1()

# Activity 2: Baby Names III
def activity2():
	import glob
	
	years = range(1880, 2019)
	data0 = count_occurrences_in_years(years, 'Heather')
	data1 = count_occurrences_in_years(years, 'Josiah')
	plot0(data0, data1, years, 'Names')
	plot1(data0, data1, years, 'Names')
	
	print("Finished Activity 2")
	
def plot0(data0, data1, years, target):
	import matplotlib.pyplot as plt
	
	counts0 = []
	for list in data0:
		counts0.append(list[0])
		
	counts1 = []
	for list in data1:
		counts1.append(list[0])
	
	plt.plot(years, counts0, linewidth=4)
	plt.plot(years, counts1, linewidth=4)
	plt.xlabel('Years', fontsize=20)
	plt.ylabel('Number of Occurrences', fontsize=20)
	plt.title('Number of Occurrences for '+target, fontsize=24)
	plt.legend(['data0', 'data1'], loc='upper left')
	plt.show()

def plot1(data0, data1, years, target):
	#	same as plot0, didnt see point in making a different one, 
	# 	but this way there are still two functions and two plots 
	#	made. Lmk if we want more different examples, it might
	# 	be useful to test each schenario given in the Activity 2 
	# 	of the lab, but probs not.
	
	import matplotlib.pyplot as plt
	
	counts0 = []
	for list in data0:
		counts0.append(list[0])
		
	counts1 = []
	for list in data1:
		counts1.append(list[0])
	
	plt.plot(years, counts0, linewidth=4)
	plt.plot(years, counts1, linewidth=4)
	plt.xlabel('Years', fontsize=20)
	plt.ylabel('Number of Occurrences', fontsize=20)
	plt.title('Number of Occurrences for '+target, fontsize=24)
	plt.legend(['data0', 'data1'], loc='upper left')
	plt.show()


activity2()