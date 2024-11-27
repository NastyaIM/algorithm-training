### ссылка на задачи : https://contest.yandex.ru/contest/66795/problems/
_____
## A. Родословная: подсчет уровней
В генеалогическом древе у каждого человека, кроме родоначальника, есть ровно один родитель. Каждому элементу дерева 
сопоставляется целое неотрицательное число, называемое высотой. У родоначальника высота равна 0, у любого другого элемента 
высота на 1 больше, чем у его родителя. Вам дано генеалогическое древо, определите высоту всех его элементов.
### Пример 1
| Ввод               | Вывод         |
|:-------------------|:--------------|
| 9                  | Alexander_I 4 |
| Alexei Peter_I     | Alexei 1      |
| Anna Peter_I       | Anna 1        |
| Elizabeth Peter_I  | Elizabeth 1   |
| Peter_II Alexei    | Nicholaus_I 4 |
| Peter_III Anna     | Paul_I 3      |
| Paul_I Peter_III   | Peter_I 0     |
| Alexander_I Paul_I | Peter_II 2    |
| Nicholaus_I Paul_I | Peter_III 2   |
### Пример 2
| Ввод                 | Вывод        |
|:---------------------|:-------------|
| 10                   | AQHFYP 3     |
| AQHFYP MKFXCLZBT     | AYKOTYQ 2    |
| AYKOTYQ QIUKGHWCDC   | IWCGKHMFM 1  |
| IWCGKHMFM WPLHJL     | MJVAURUDN 2  |
| MJVAURUDN QIUKGHWCDC | MKFXCLZBT 2  |
| MKFXCLZBT IWCGKHMFM  | PUTRIPYHNQ 2 |
| PUTRIPYHNQ UQNGAXNP  | QIUKGHWCDC 1 |
| QIUKGHWCDC WPLHJL    | UQNGAXNP 1   |
| UQNGAXNP WPLHJL      | WPLHJL 0     |
| YURTPJNR QIUKGHWCDC  | YURTPJNR 2   |
### Пример 3
| Ввод                 | Вывод        |
|:---------------------|:-------------|
| 10                   | BFNRMLH 3    |
| BFNRMLH CSZMPFXBZ    | CSZMPFXBZ 2  |
| CSZMPFXBZ IHWBQDJ    | FMVQTU 9     |
| FMVQTU FUXATQUGIG    | FUXATQUGIG 8 |
| FUXATQUGIG IRVAVMQKN | GNVIZ 6      |
| GNVIZ IQGIGUJZ       | IHWBQDJ 1    |
| IHWBQDJ LACXYFQHSQ   | IQGIGUJZ 5   |
| IQGIGUJZ JMUPNYRQD   | IRVAVMQKN 7  |
| IRVAVMQKN GNVIZ      | JMUPNYRQD 4  |
| JMUPNYRQD BFNRMLH    | LACXYFQHSQ 0 |
____
## B. Родословная: число потомков
В генеалогическом древе у каждого человека, кроме родоначальника, есть ровно один родитель.  
Для каждого элемента дерева определите число всех его потомков (не считая его самого).
### Пример
| Ввод               | Вывод         |
|:-------------------|:--------------|
| 9                  | Alexander_I 0 |
| Alexei Peter_I     | Alexei 1      |
| Anna Peter_I       | Anna 4        |
| Elizabeth Peter_I  | Elizabeth 0   |
| Peter_II Alexei    | Nicholaus_I 0 |
| Peter_III Anna     | Paul_I 2      |
| Paul_I Peter_III   | Peter_I 8     |
| Alexander_I Paul_I | Peter_II 0    |
| Nicholaus_I Paul_I | Peter_III 3   |
____
## C. Родословная: LCA
В генеалогическом древе определите для двух элементов их наименьшего общего предка. Наименьшим общим предком элементов 
A и B является такой элемент C, что С является предком A, C является предком B, при этом глубина C является наибольшей 
из возможных. При этом элемент считается своим собственным предком.
### Пример
| Ввод                    | Вывод   |
|:------------------------|:--------|
| 9                       | Paul_I  |
| Alexei Peter_I          | Peter_I |
| Anna Peter_I            | Anna    |
| Elizabeth Peter_I       |         |
| Peter_II Alexei         |         |
| Peter_III Anna          |         |
| Paul_I Peter_III        |         |
| Alexander_I Paul_I      |         |
| Nicholaus_I Paul_I      |         |
| Alexander_I Nicholaus_I |         |
| Peter_II Paul_I         |         |
| Alexander_I Anna        |         |
____
Напишите программу, которая будет реализовывать действия в бинарном дереве поиска «вставить» и «найти» (по значению). 
Программа должна обрабатывать запросы трёх видов:
- ADD n — если указанного числа еще нет в дереве, вставлять его и выводить слово «DONE», если уже есть — оставлять дерево 
как было и выводить слово «ALREADY».
- SEARCH — следует выводить слово «YES» (если значение найдено в дереве) или слово «NO» (если не найдено). Дерево при этом не меняется. 
- PRINTTREE — выводить все дерево, обязательно используя алгоритм, указанный в формате вывода результатов.
### Пример
| Ввод      | Вывод   |
|:----------|:--------|
| ADD 2     | DONE    |
| ADD 3     | DONE    |
| ADD 2     | ALREADY |
| SEARCH 2  | YES     |
| ADD 5     | DONE    |
| PRINTTREE | 2       |
| SEARCH 7  | .3      |
|           | ..5     |
|           | NO      |
____