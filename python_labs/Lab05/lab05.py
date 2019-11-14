# https://www.onlinegdb.com/online_python_compiler
# 
#


# Activity 0
def activity0():
	import glob
	
	for filepath in glob.glob("names/*.txt"):
		result = count_occurrences_in_file(filepath, "Josiah")
		#print(filepath, ": ", result)
		
	result = count_occurrences_in_years(range(2000, 2018), "Josiah")
	'''
	for list in result:
		#print(list)
	'''
	'''
	years = range(1880, 2019)
	data = count_occurrences_in_years(years, 'Josiah')
	plot_national_occurrences(data, years, 'Josiah')
	'''
	
	print("Finished Activity 0")

def count_occurrences_in_file(path, target):
	''' Return the count for the target name and the total count for the year '''
	
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
	''' Return a list of occurrences for the range of years '''
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
	print("How about I don't")
	'''
	import matplotlib.pyplot as plt
	
	counts = []
	for list in data:
		counts.append(list[0])
	
	plt.plot(years, counts, linewidth=4)
	plt.xlabel('Years', fontsize=20)
	plt.ylabel('Number of Occurrences', fontsize=20)
	plt.title('Number of Occurrences for '+target, fontsize=24)
	plt.show()
	'''


activity0();

# Activity 1
def activity1():
	import glob

	
	for filepath in glob.glob("namesbystate/*.txt"):
		result = filter_occurrences_in_file(filepath, "Nova")
		#print(filepath, ": ", result)
	
	for filepath in glob.glob("namesbystate/*.txt"):
		result = count_yearly_totals(filepath)
		#print(filepath, ": ", result)

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
	''' Return a dict with the total number of occurrences in each year. ''' 
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
	''' Plot the number of occurrences for the state data '''
	print("How about I don't")
	
def plot_state_percentages(data, totals, target):
	''' Plot the percentages of occurrences for the state data '''
	print("How about I don't")

activity1()