JAVA11 String 新增API：
String repeat​(int count)
： Returns a string whose value is the concatenation of thisstring repeated count times. 

boolean isBlank()
： Returns true if the string is empty or contains only white space codepoints,otherwise false. 

String strip()
：Returns a string whose value is this string, with all leadingand trailing white spaceremoved.
 
String stripLeading()
：Returns a string whose value is this string, with all leading white space removed.
 
String stripTrailing()
：Returns a string whose value is this string, with all trailing white space removed. 

Stream<String> lines()
Returns a stream of lines extracted from this string,separated by line terminators. 