Laboratoire 3 de programmation Concurrente

Utilisation :

Il suffit de saisir ses configs d�sir�es et de cocher la bonne version dans laquelle on aimerait avoir notre application.

Cocher la case mode test va rien afficher mais fera les proc�dures dans le fond et affichera dans le terminal les temps obtenus de comparaisons et
de performances. L'application sera bloqu�e tant que tous les tests ne seront pas finis. 

Si l'utilisateur coche la case Blocking Queue ou Circular Buffer (ou les deux), cela va lancer les frames correspondantes et affichera les 
avions pass�s dans chacunes des �tapes. Cela permettra de voir le bon fonctionnement de l'application.
C'est ce qui se d�roule dans le mode test sauf que nous n'affichons rien de ce qu'il se passe.

Notes pour killian --> ci dessous tu as les temps que j'ai eu avec mon ordi, je pense que ce serait bien que tu fasses pareil avec le tiens.
comme expliqu�, il suffit que tu lances l'appli en mode test et d'attendre de voir les r�sultats dans le terminal. 
Fait les tests avec le temps al�atoire et fix� � 1000 ms. (pour changer le temps, va dans les classes AvionBlockinQueue et AvionCircularBuffer et 
change le temps des wait dans la fonction run). Efface ce commentaire dans le rapport bien �videmment xD.
pour le rapport justement, il te suffit de faire 2,3 pages je pense. j'ai enti�rement comment� le code, il n'est vraiment pas difficile � comprendre.
Demande moi si jamais. Va voir le cahier des charges et je pense que tu peux expliquer chaque point du chapite travail � rendre. Du moins c'est ce que je ferais.
Tu expliques un peu le comportement g�n�ral une fois que tu as lu mes commentaires. Ce n'est pas fonci�rement compliqu� je pense. merci si tu arrives � le faire.

Test de temps avec l'ordinateur de Pedro (processeur avec 2 coeur phyisque --> 4 logiques) : 

- Temps avec des wait fix�s al�atoirement -- Les configurations sont fix�es ainsi : 20 avions, 2 pistes d'arriv�es, 2 pistes de d�parts, 2 places de parking
	
******************************D�but de la s�quence de test de performances******************************
D�marrage du test de la Blocking Queue
Temps d'ex�cution : 30269 ms

D�marrage du test du Circular Buffer
Temps d'ex�cution : 28637 ms
*******************************Fin de la s�quence de test de performances*******************************

********************D�but de la s�quence des tests d'influence sur la Blocking Queue********************
D�marrage du test de la variation du nombre de pistes d'arriv�e
Nombre de piste(s) : 1 -- Temps : 45094 ms
Nombre de piste(s) : 2 -- Temps : 29267 ms
Nombre de piste(s) : 3 -- Temps : 28899 ms
Nombre de piste(s) : 4 -- Temps : 29455 ms
Nombre de piste(s) : 5 -- Temps : 29658 ms
Nombre de piste(s) : 6 -- Temps : 27549 ms
Nombre de piste(s) : 7 -- Temps : 28002 ms
Nombre de piste(s) : 8 -- Temps : 27690 ms
Nombre de piste(s) : 9 -- Temps : 29279 ms
Nombre de piste(s) : 10 -- Temps : 30125 ms
Nombre de piste(s) : 11 -- Temps : 29332 ms
Nombre de piste(s) : 12 -- Temps : 26788 ms
Nombre de piste(s) : 13 -- Temps : 27887 ms
Nombre de piste(s) : 14 -- Temps : 28516 ms
Nombre de piste(s) : 15 -- Temps : 27288 ms
Nombre de piste(s) : 16 -- Temps : 27668 ms
Nombre de piste(s) : 17 -- Temps : 26261 ms
Nombre de piste(s) : 18 -- Temps : 28254 ms
Nombre de piste(s) : 19 -- Temps : 28237 ms
Nombre de piste(s) : 20 -- Temps : 28764 ms
Fin du test de la variation du nombre de pistes d'arriv�e !

D�marrage du test de la variation du nombre de pistes de d�parts
Nombre de piste(s) : 1 -- Temps : 43412 ms
Nombre de piste(s) : 2 -- Temps : 26707 ms
Nombre de piste(s) : 3 -- Temps : 26069 ms
Nombre de piste(s) : 4 -- Temps : 23720 ms
Nombre de piste(s) : 5 -- Temps : 23570 ms
Nombre de piste(s) : 6 -- Temps : 26509 ms
Nombre de piste(s) : 7 -- Temps : 23218 ms
Nombre de piste(s) : 8 -- Temps : 22861 ms
Nombre de piste(s) : 9 -- Temps : 25276 ms
Nombre de piste(s) : 10 -- Temps : 24630 ms
Nombre de piste(s) : 11 -- Temps : 25125 ms
Nombre de piste(s) : 12 -- Temps : 25855 ms
Nombre de piste(s) : 13 -- Temps : 25398 ms
Nombre de piste(s) : 14 -- Temps : 24761 ms
Nombre de piste(s) : 15 -- Temps : 24189 ms
Nombre de piste(s) : 16 -- Temps : 27069 ms
Nombre de piste(s) : 17 -- Temps : 23659 ms
Nombre de piste(s) : 18 -- Temps : 29307 ms
Nombre de piste(s) : 19 -- Temps : 24787 ms
Nombre de piste(s) : 20 -- Temps : 24607 ms
Fin du test de la variation du nombre de pistes de d�parts !

D�marrage du test de la variation du nombre de place dans le parking
Nombre de place(s) : 1 -- Temps : 47634 ms
Nombre de place(s) : 2 -- Temps : 24470 ms
Nombre de place(s) : 3 -- Temps : 19184 ms
Nombre de place(s) : 4 -- Temps : 15744 ms
Nombre de place(s) : 5 -- Temps : 12808 ms
Nombre de place(s) : 6 -- Temps : 14017 ms
Nombre de place(s) : 7 -- Temps : 12934 ms
Nombre de place(s) : 8 -- Temps : 11178 ms
Nombre de place(s) : 9 -- Temps : 11397 ms
Nombre de place(s) : 10 -- Temps : 9946 ms
Nombre de place(s) : 11 -- Temps : 10882 ms
Nombre de place(s) : 12 -- Temps : 10503 ms
Nombre de place(s) : 13 -- Temps : 9889 ms
Nombre de place(s) : 14 -- Temps : 9693 ms
Nombre de place(s) : 15 -- Temps : 10485 ms
Nombre de place(s) : 16 -- Temps : 9468 ms
Nombre de place(s) : 17 -- Temps : 9339 ms
Nombre de place(s) : 18 -- Temps : 10142 ms
Nombre de place(s) : 19 -- Temps : 10514 ms
Nombre de place(s) : 20 -- Temps : 9304 ms
Fin du test de la variation du nombre de place dans le parking !




- Temps avec des waits fix�s � 1000 ms -- M�me configurations

******************************D�but de la s�quence de test de performances******************************
D�marrage du test de la Blocking Queue
Temps d'ex�cution : 13096 ms

D�marrage du test du Circular Buffer
Temps d'ex�cution : 13091 ms
*******************************Fin de la s�quence de test de performances*******************************

********************D�but de la s�quence des tests d'influence sur la Blocking Queue********************
D�marrage du test de la variation du nombre de pistes d'arriv�e
Nombre de piste(s) : 1 -- Temps : 23090 ms
Nombre de piste(s) : 2 -- Temps : 13080 ms
Nombre de piste(s) : 3 -- Temps : 13126 ms
Nombre de piste(s) : 4 -- Temps : 13083 ms
Nombre de piste(s) : 5 -- Temps : 13080 ms
Nombre de piste(s) : 6 -- Temps : 13079 ms
Nombre de piste(s) : 7 -- Temps : 13079 ms
Nombre de piste(s) : 8 -- Temps : 13077 ms
Nombre de piste(s) : 9 -- Temps : 13079 ms
Nombre de piste(s) : 10 -- Temps : 13077 ms
Nombre de piste(s) : 11 -- Temps : 13079 ms
Nombre de piste(s) : 12 -- Temps : 13148 ms
Nombre de piste(s) : 13 -- Temps : 13129 ms
Nombre de piste(s) : 14 -- Temps : 13113 ms
Nombre de piste(s) : 15 -- Temps : 13089 ms
Nombre de piste(s) : 16 -- Temps : 13096 ms
Nombre de piste(s) : 17 -- Temps : 13104 ms
Nombre de piste(s) : 18 -- Temps : 13084 ms
Nombre de piste(s) : 19 -- Temps : 13077 ms
Nombre de piste(s) : 20 -- Temps : 13075 ms
Fin du test de la variation du nombre de pistes d'arriv�e !

D�marrage du test de la variation du nombre de pistes de d�parts
Nombre de piste(s) : 1 -- Temps : 23084 ms
Nombre de piste(s) : 2 -- Temps : 13089 ms
Nombre de piste(s) : 3 -- Temps : 13082 ms
Nombre de piste(s) : 4 -- Temps : 13076 ms
Nombre de piste(s) : 5 -- Temps : 13078 ms
Nombre de piste(s) : 6 -- Temps : 13121 ms
Nombre de piste(s) : 7 -- Temps : 13079 ms
Nombre de piste(s) : 8 -- Temps : 13074 ms
Nombre de piste(s) : 9 -- Temps : 13080 ms
Nombre de piste(s) : 10 -- Temps : 13077 ms
Nombre de piste(s) : 11 -- Temps : 13076 ms
Nombre de piste(s) : 12 -- Temps : 13076 ms
Nombre de piste(s) : 13 -- Temps : 13078 ms
Nombre de piste(s) : 14 -- Temps : 13077 ms
Nombre de piste(s) : 15 -- Temps : 13077 ms
Nombre de piste(s) : 16 -- Temps : 13075 ms
Nombre de piste(s) : 17 -- Temps : 13075 ms
Nombre de piste(s) : 18 -- Temps : 13077 ms
Nombre de piste(s) : 19 -- Temps : 13125 ms
Nombre de piste(s) : 20 -- Temps : 13101 ms
Fin du test de la variation du nombre de pistes de d�parts !

D�marrage du test de la variation du nombre de place dans le parking
Nombre de place(s) : 1 -- Temps : 23078 ms
Nombre de place(s) : 2 -- Temps : 13075 ms
Nombre de place(s) : 3 -- Temps : 10073 ms
Nombre de place(s) : 4 -- Temps : 8075 ms
Nombre de place(s) : 5 -- Temps : 7076 ms
Nombre de place(s) : 6 -- Temps : 7072 ms
Nombre de place(s) : 7 -- Temps : 6074 ms
Nombre de place(s) : 8 -- Temps : 6073 ms
Nombre de place(s) : 9 -- Temps : 6073 ms
Nombre de place(s) : 10 -- Temps : 5072 ms
Nombre de place(s) : 11 -- Temps : 5074 ms
Nombre de place(s) : 12 -- Temps : 5074 ms
Nombre de place(s) : 13 -- Temps : 5073 ms
Nombre de place(s) : 14 -- Temps : 5074 ms
Nombre de place(s) : 15 -- Temps : 5074 ms
Nombre de place(s) : 16 -- Temps : 5072 ms
Nombre de place(s) : 17 -- Temps : 5071 ms
Nombre de place(s) : 18 -- Temps : 5070 ms
Nombre de place(s) : 19 -- Temps : 5073 ms
Nombre de place(s) : 20 -- Temps : 4074 ms
Fin du test de la variation du nombre de place dans le parking !

*********************Fin de la s�quence des tests d'influence sur la Blocking Queue*********************

