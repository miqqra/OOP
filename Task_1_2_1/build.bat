mkdir .\build_project\doc\
javadoc -d .\build_project\doc\ -charset utf-8 -sourcepath .\src\main\java\ -subpackages ru.nsu.krasnikov

mkdir .\build_project\bin\
javac -d .\build_project\bin\ .\src\main\java\ru\nsu\krasnikov\StackInterface.java .\src\main\java\ru\nsu\krasnikov\MyStack.java

mkdir .\build_project\jar\
jar cf .\build_project\jar\StackInterface.jar .\src\main\java\ru\nsu\krasnikov\StackInterface.java
jar cf .\build_project\jar\MyStack.jar .\src\main\java\ru\nsu\krasnikov\MyStack.java