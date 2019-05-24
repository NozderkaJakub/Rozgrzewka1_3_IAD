import matplotlib.pyplot as plt
import sys
import csv

x = []
y = []

for i in range(int(sys.argv[2])):
    with open(sys.argv[1] + str(i) + ".txt",'r') as csvfile:
        plots = csv.reader(csvfile, delimiter=';')
        for row in plots:
            x.append(float(row[0]))
            y.append(float(row[1]))
    if i == 0: plt.plot(x, y, 'k', label="K=4")
    else: plt.plot(x, y, 'r')
    x = []
    y = []

plt.xlabel('X')
plt.ylabel('Y')
plt.title('RBF')
plt.grid(True)
plt.legend()
plt.show()
plt.savefig("Out" + str(int(sys.argv[2]) - 1) + ".png")