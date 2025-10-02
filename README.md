# DisplayChanger
A simple Java project that I created to fix one of my problems. I quite frequently
switch between "Extend these displays" and "Show only on 2" in the Windows Display Settings.
I created this program to make a quick toggle that I can pin to my taskbar instead of going
through the settings every time.

Thought of the idea when looking through the win32 api documentation and
found a table which shows values for the display topology flags, [available here](https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-setdisplayconfig).

# Building to a JAR
Using maven, run the following command in the project directory:
```
mvn clean package
```
This will create a JAR file in the `target` directory.

# Building to an EXE
After building the JAR, use [Launch4j](https://launch4j.sourceforge.net/).
Set the output file to `DisplayChanger.exe` and the jar to the JAR file created
in the previous step.

(You can use other tools, this is just what I use)

# License
This project is licensed under the MIT License. See the LICENSE file for details.