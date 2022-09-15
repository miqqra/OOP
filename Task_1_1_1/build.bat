mkdir .\build_project\doc\
javadoc -d .\build_project\doc\ -charset utf-8 -sourcepath .\src\main\java\ -subpackages ru.nsu.krasnikov

mkdir .\build_project\bin\
javac -d .\build_project\bin\ .\src\main\java\ru\nsu\krasnikov\Heap.java

mkdir .\build_project\jar\
jar cf .\build_project\jar\HeapSort.jar .\src\main\java\ru\nsu\krasnikov\Heap.java