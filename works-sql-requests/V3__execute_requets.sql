/*Exercice 1
Soit la base de données d’un festival de musique. Dans une représentation peut participer un ou plusieurs musiciens. Un musicien ne peut participer qu’à une seule représentation.
– Representation (num_rep, titre_rep , lieu)
– Musicien (num_mus , nom , #num_rep)
– Programmer (date ,#num_rep, tarif)
Ecrire la commande SQL permettant de rechercher:
1. La liste des titres des représentations.
2. La liste des titres des représentations ayant lieu au “théâtre alyssa”.
3. La liste des noms des musiciens et des titres et les titres des représentations auxquelles ils participent.
4. La liste des titres des représentations, les lieux et les tarifs du 25/07/2008
5. Le nombre des musiciens qui participent à la représentations n°20
6. Les représentations et leurs dates dont le tarif ne dépasse pas 100DH
*/
select count(*), lieu from sql_req_exo.Representation group by lieu order by lieu, count(*) desc ;
select titre_rep, lieu from sql_req_exo.Representation; #1
select R.titre_rep, R.lieu from Representation R where R.lieu= 'Lieu B';#2
select M.nom, R.titre_rep from Musicien M inner join Representation R on M.num_rep=R.num_rep;#3
select R.titre_rep, R.lieu, P.tarif from Representation R inner join Programmer P on R.num_rep = P.num_rep; #4
select count(*) as nbr_musiciens from Musicien M where M.num_rep=20; #5
select R.titre_rep, R.lieu, P.tarif from Representation R inner join Programmer P on R.num_rep = P.num_rep where P.tarif <=100; #6
#--------------------------------------------------------------------------------------------#
/*
Exercice 2
Soit la base de données suivante :
— Departements :(DNO, DNOM, DIR, VILLE);
— Employes : (ENO, ENOM, PROF, DATEEMB, SAL, COMM,#DNO);
Exprimez en SQL les requêtes suivantes :
1. Donnez la liste des employés ayant une commission
2. Donnez les noms, emplois et salaires des employés par emploi croissant, et pour chaque emploi, par salaire décroissant
3. Donnez le salaire moyen des employés
4. Donnez le salaire moyen du département Production
5. Donnez les numéros de département et leur salaire maximum
6. Donnez les différentes professions et leur salaire moyen
7. Donnez les salaires moyens par profession des 3 professions les plus bas
8. Donnez le ou les emplois ayant le salaire moyen le plus bas, ainsi que ce salaire moyen
*/

select * from Employes E where E.COMM is not null; #1
select E.ENOM, E.PROF, E.SAL from Employes E order by E.PROF, E.SAL desc ;#2
select avg(E.SAL) as sal_moy from Employes E;#3
select avg(E.SAL) as sal_moy from Employes E inner  join Departements D on E.DNO = D.DNO where D.DNOM='Department 1';#4
select max(E.SAL) as max_sal, E.DNO from Employes E group by E.DNO; #5
select E.PROF, avg(E.SAL) from Employes E group by E.PROF; #6
select avg(E.SAL) as salaire_moyen from Employes E group by E.PROF order by  salaire_moyen limit 3;#7
select  E.PROF from Employes E group by E.PROF having avg(E.SAL) = (select avg(SAL) from sql_req_exo.Employes E group by PROF order by avg(SAL) limit 1);#8