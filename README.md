# Russian-Transliteration
Please note: This is a transliteration converter
(to convert between Cyrillic and Latin alphabets back and forth), not a translator.

Switch: change between the two Alphabets

Name: if false then е -> e, ё -> ё, й -> i, ю -> iu, я -> ia. Otherwise,
е -> ye, ё -> yo, й -> j, ю -> yu, я -> ya.

Silent sound: if true then ь -> ', ъ -> ". Otherwise, both will be silent (empty string).
Note: If silent sound is false, it will cause the transliteration to be inaccurate when converting words with
ь or ъ. Also, I haven't figured out how to differentiate between Ь Ъ and ь ъ, therefore the transliteration from Latin
to Russian between lower and upper will result in upper (Ь Ъ)

Resizable: if true, then you can resize the window. If false, you can't resize the window. That's it)

Choose text: Choose text to upload to convert to either Russian or Latin. Note: only files ending with .txt
are accepted, *.txt.exe or *.txt.jpg or *.* are not accepted

# Run file
Requires JavaFX. In IntelliJ IDE, in New Project Generators choose JavaFX with language as Java and build as Maven.
The main file containing the GUI is RusToLatinGUI.java
