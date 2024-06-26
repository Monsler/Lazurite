<div align="center">
  <img src="icon.png" width="300">
</div>

## More About Languge
>To work you need to install jdk 19

This language takes advantage of Java and simplifies it. This makes Lazurite quite simple. Basically, the language is used to create games and applications, because it has powerful libraries for this. But on it you can make bots for social networks, work with files, and much more.

Hello World in Lazurite!:

```shell
print("Hello World!")
```
lsoup:
```cpp
using "lsoup"

lsoup.parse("https://www.lipsum.com")

title = lsoup.select("title")
text = lsoup.select("h3")

println(title)
print(text)
```

async example:
```cpp
using "async"
func hel(arg){
    print(arg)
}
async.supply(hel("Hello"))
```
graph example:
```cpp
using "graph"
Frame()

fill(100,100,200)
rect(10,10,200,100)

fill(100,200,100)
lrect(100,100,100,100)
```
thread example:
```cpp
using "std"
func th(arg){
   println("My " + str(arg) + " Thread")
}
for(i=0, i<10, i++){
    std.thread(::th, i)
}
```

## Download

Download jar and exe files of the language interpreter can be downloaded in the <a href = "https://github.com/ArtyomKingmang/Lazurite/releases">Releases</a> tab)

## Why Lazurite?
Why should you choose Lazurite?:

- Simple syntax.
- Using java features and simplifying them
- Flexible syntax
- Dynamic typing
- Object-Oriented Language.


## License
Lazurite is relseased under <a href="https://github.com/ArtyomKingmang/Lazurite/wiki">MIT License 2.0</a>

See more about it!


