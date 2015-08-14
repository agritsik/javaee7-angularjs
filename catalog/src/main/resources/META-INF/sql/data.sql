# examples from https://en.wikipedia.org/wiki/List_of_top-division_football_clubs_in_UEFA_countries#Italy

insert into players set id=1, name='Lionel Messi';
insert into players set id=2, name='Neymar';
insert into players set id=3, name='Dani Alves';
insert into players set id=4, name='Andrés Iniesta';

insert into countries set id=1, name='Spain';
insert into countries set id=2, name='Germany';
insert into countries set id=3, name='Italy';

insert into clubs set id=1, country_id=1, name='FC Barcelona';
insert into clubs set id=2, country_id=1, name='Real Madrid C.F.';
insert into clubs set id=3, country_id=1, name='Valencia CF';
insert into clubs set id=4, country_id=2, name='FC Bayern München';
insert into clubs set id=5, country_id=2, name='Borussia Dortmund';
insert into clubs set id=6, country_id=3, name='Juventus F.C.';
insert into clubs set id=7, country_id=3, name='Juventus F.C.';
insert into clubs set id=8, country_id=3, name='A.C. Milan';
insert into clubs set id=9, country_id=3, name='F.C. Internazionale Milano';