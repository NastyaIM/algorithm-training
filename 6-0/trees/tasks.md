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