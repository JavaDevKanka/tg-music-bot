create table IF NOT EXISTS music
(
    id      uuid        not null primary key,
    song_name varchar(230) not null unique,
    author  varchar(85) not null,
    country varchar(35) not null
);

create table IF NOT EXISTS song
(
    id           uuid         not null primary key,
    created      timestamp    not null,
    style        varchar(50)  not null,
    release_date timestamp    not null,
    music_id uuid references music(id)
);

create index MBT_STYLE_IDX on song (style);
create index MBT_NAME_IDX on music (song_name);

create table IF NOT EXISTS swear_word(
    id bigserial not null primary key,
    word varchar(120) not null unique
);

insert into swear_word (word)
values ('spam'),
       ('аборт'),
       ('анус'),
       ('беспезды'),
       ('бздун'),
       ('бздюх'),
       ('блудилище'),
       ('бля'),
       ('бляд'),
       ('блядво'),
       ('блядеха'),
       ('бляди'),
       ('блядина'),
       ('блядистка'),
       ('блядище'),
       ('блядки'),
       ('блядование'),
       ('блядовать'),
       ('блядовитый'),
       ('блядовозка'),
       ('блядолиз'),
       ('блядоход'),
       ('блядский'),
       ('блядство'),
       ('блядствовать'),
       ('блядун'),
       ('блядь'),
       ('блядюга'),
       ('блядюра'),
       ('блядюшка'),
       ('блядюшник'),
       ('бордель'),
       ('вагина'),
       ('вафлист'),
       ('вжопить'),
       ('вжопиться'),
       ('вздрачивание'),
       ('вздрачивать'),
       ('вздрачиваться'),
       ('вздрочить'),
       ('вздрочиться'),
       ('вздрючивание'),
       ('вздрючивать'),
       ('вздрючить'),
       ('взъебка'),
       ('взъебнуть'),
       ('взъебщик'),
       ('вислозадая'),
       ('влагалище'),
       ('вхуйнуть'),
       ('вхуйнуться'),
       ('вхуя'),
       ('вхуякать'),
       ('вхуякаться'),
       ('вхуякивать'),
       ('вхуякиваться'),
       ('вхуякнуть'),
       ('вхуякнуться'),
       ('вхуяривание'),
       ('вхуяривать'),
       ('вхуяриваться'),
       ('вхуярить'),
       ('вхуяриться'),
       ('вхуячивание'),
       ('вхуячивать'),
       ('вхуячиваться'),
       ('вхуячить'),
       ('вхуячиться'),
       ('вхуяшивать'),
       ('вхуяшиваться'),
       ('вхуяшить'),
       ('вхуяшиться'),
       ('въебать'),
       ('въебаться'),
       ('въебашивать'),
       ('въебашиваться'),
       ('въебашить'),
       ('въебашиться'),
       ('въебенивать'),
       ('въебениваться'),
       ('въебенить'),
       ('въебениться'),
       ('выблядок'),
       ('выебанный'),
       ('выебат'),
       ('выебать'),
       ('выебаться'),
       ('высераться'),
       ('высрать'),
       ('высраться'),
       ('выссать'),
       ('выссаться'),
       ('выссереть'),
       ('говнецо'),
       ('говнистый'),
       ('говниться'),
       ('говно'),
       ('говновоз'),
       ('говнодав'),
       ('говноеб'),
       ('говноед'),
       ('говномер'),
       ('говномес'),
       ('говносерка'),
       ('говнюк'),
       ('голожопая'),
       ('гомик'),
       ('гомосек'),
       ('гондон'),
       ('гонорея'),
       ('давалка'),
       ('двужопник'),
       ('дерьмо'),
       ('дерьмовый'),
       ('дерьмоед'),
       ('дилдо'),
       ('додрочить'),
       ('додрочиться'),
       ('доебать'),
       ('доебаться'),
       ('доебенивать'),
       ('доебениваться'),
       ('доебенить'),
       ('доебениться'),
       ('долбоеб'),
       ('допиздить'),
       ('допиздиться'),
       ('допиздоболивать'),
       ('допиздоболиваться'),
       ('допиздоболиться'),
       ('допиздовать'),
       ('допиздоваться'),
       ('допиздовывать'),
       ('допиздовываться'),
       ('допиздохать'),
       ('допиздохаться'),
       ('допиздохивать'),
       ('допиздохиваться'),
       ('допиздошивать'),
       ('допиздошиваться'),
       ('допиздошить'),
       ('допиздошиться'),
       ('допиздюкать'),
       ('допиздюкаться'),
       ('допиздюкивать'),
       ('допиздюкиваться'),
       ('допиздюливать'),
       ('допиздюливаться'),
       ('допиздюлить'),
       ('допиздюлиться'),
       ('допиздюривать'),
       ('допиздюриваться'),
       ('допиздюрить'),
       ('допиздюриться'),
       ('допиздюхать'),
       ('допиздюхаться'),
       ('допиздюхивать'),
       ('допиздюхиваться'),
       ('допиздякать'),
       ('допиздякаться'),
       ('допиздякивать'),
       ('допиздякиваться'),
       ('допиздяривать'),
       ('допиздяриваться'),
       ('допиздярить'),
       ('допиздяриться'),
       ('допиздяхать'),
       ('допиздяхаться'),
       ('допиздяхивать'),
       ('допиздяхиваться'),
       ('допиздячивать'),
       ('допиздячиваться'),
       ('допиздячить'),
       ('допиздячиться'),
       ('допиздяшивать'),
       ('допиздяшиваться'),
       ('допиздяшить'),
       ('допиздяшиться'),
       ('допизживать'),
       ('дотрахать'),
       ('дотрахаться'),
       ('дохуйнуть'),
       ('дохуякать'),
       ('дохуякаться'),
       ('дохуякивать'),
       ('дохуякиваться'),
       ('дохуяривать'),
       ('дохуяриваться'),
       ('дохуярить'),
       ('дохуяриться'),
       ('дохуячивать'),
       ('дохуячиваться'),
       ('дохуячить'),
       ('дохуячиться'),
       ('дрисня'),
       ('дристать'),
       ('дристун'),
       ('дроченье'),
       ('дрочилыцик'),
       ('дрочить'),
       ('дрочиться'),
       ('дрочка'),
       ('дрючить'),
       ('дрючиться'),
       ('дурак'),
       ('дуроеб'),
       ('ебало'),
       ('ебальник'),
       ('ебальные'),
       ('ебальный'),
       ('ебанатик'),
       ('ебанашка'),
       ('ебанутый'),
       ('ебануть'),
       ('ебануться'),
       ('ебат'),
       ('ебать'),
       ('ебатьс'),
       ('ебаться'),
       ('ебитесь'),
       ('ебло'),
       ('еблом'),
       ('еблысь'),
       ('ебля'),
       ('ебнуть'),
       ('ебнуться'),
       ('ебня'),
       ('ебучий'),
       ('жирнозадый'),
       ('жопа'),
       ('жопастая'),
       ('жопенци'),
       ('жопища'),
       ('жопка'),
       ('жопник'),
       ('жопоеб'),
       ('жопой'),
       ('жополиз'),
       ('жополизание'),
       ('жопоногий'),
       ('жопочка'),
       ('жопочник'),
       ('жопство'),
       ('жопу'),
       ('забздеть'),
       ('заблядовать'),
       ('заблядоваться'),
       ('задница'),
       ('задрачивать'),
       ('задрачиваться'),
       ('задроченный'),
       ('задрочить'),
       ('задрочиться'),
       ('задрючить'),
       ('задрючиться'),
       ('заебанный'),
       ('заебательская'),
       ('заебать'),
       ('заебаться'),
       ('заебашивать'),
       ('заебашиваться'),
       ('заебашить'),
       ('заебашиться'),
       ('заебенивать'),
       ('заебениваться'),
       ('заебенить'),
       ('заебениться'),
       ('заебла'),
       ('залупа'),
       ('залупаться'),
       ('залупенить'),
       ('залупень'),
       ('залупистый'),
       ('залупить'),
       ('залупляться'),
       ('залупу'),
       ('запиздарить'),
       ('запизденелый'),
       ('запизденная'),
       ('запиздить'),
       ('запиздиться'),
       ('запиздоболивать'),
       ('запиздоболиваться'),
       ('запиздоболить'),
       ('запиздоболиться'),
       ('запиздовать'),
       ('запиздоваться'),
       ('запиздовывать'),
       ('запиздовываться'),
       ('запиздохать'),
       ('запиздошивать'),
       ('запиздошиваться'),
       ('запиздошить'),
       ('запиздошиться'),
       ('запиздюкать'),
       ('запиздюкаться'),
       ('запиздюкивать'),
       ('запиздюкиваться'),
       ('запиздюливать'),
       ('запиздюливаться'),
       ('запиздюлить'),
       ('запиздюлиться'),
       ('запиздюривать'),
       ('запиздюриваться'),
       ('запиздюрить'),
       ('запиздюриться'),
       ('запиздюхать'),
       ('запиздюхаться'),
       ('запиздюхивать'),
       ('запиздюхиваться'),
       ('запиздючивать'),
       ('запиздючиваться'),
       ('запиздючить'),
       ('запиздючиться'),
       ('засранец'),
       ('засранка'),
       ('засранный'),
       ('засратый'),
       ('засрать'),
       ('засраться'),
       ('зассать'),
       ('затраханный'),
       ('затрахать'),
       ('затрахаться'),
       ('затрахивать'),
       ('затрахиваться'),
       ('захуить'),
       ('захуйнуть'),
       ('захуйнуться'),
       ('захуякать'),
       ('захуякаться'),
       ('захуякивать'),
       ('захуякиваться'),
       ('захуяривать'),
       ('захуяриваться'),
       ('захуярить'),
       ('захуяриться'),
       ('захуячивать'),
       ('захуячиваться'),
       ('захуячить'),
       ('захуячиться'),
       ('захуяшивать'),
       ('захуяшиваться'),
       ('захуяшить'),
       ('захуяшиться'),
       ('злоебучий'),
       ('издрочиться'),
       ('измандить'),
       ('измандиться'),
       ('измандовать'),
       ('измандоваться'),
       ('измандовывать'),
       ('измандовываться'),
       ('изъеб'),
       ('изъебать'),
       ('изъебаться'),
       ('изъебашивать'),
       ('изъебашиваться'),
       ('изъебашить'),
       ('изъебашиться'),
       ('изъебенивать'),
       ('изъебениваться'),
       ('изъебенить'),
       ('изъебениться'),
       ('испиздеться'),
       ('испиздить'),
       ('испражнение'),
       ('испражняться'),
       ('исхуякать'),
       ('исхуякаться'),
       ('исхуякивать'),
       ('исхуякиваться'),
       ('исхуяривать'),
       ('исхуярить'),
       ('исхуяриться'),
       ('какать'),
       ('какашка'),
       ('кастрат'),
       ('кастрировать'),
       ('клитор'),
       ('клоака'),
       ('кнахт'),
       ('кончить'),
       ('косоебить'),
       ('косоебиться'),
       ('кривохуй'),
       ('курва'),
       ('курвиный'),
       ('лахудра'),
       ('лох'),
       ('лохматка'),
       ('лохудра'),
       ('манда'),
       ('мандавоха'),
       ('мандавошка'),
       ('мандить'),
       ('мандиться'),
       ('мандоватая'),
       ('мандовать'),
       ('мандохать'),
       ('мандохаться'),
       ('мандохивать'),
       ('мандохиваться'),
       ('мандошить'),
       ('мастурбатор'),
       ('минет'),
       ('минетить'),
       ('минетка'),
       ('минетчик'),
       ('минетчица'),
       ('мозгоеб'),
       ('мозгоебатель'),
       ('мозгоебать'),
       ('мозгоебка'),
       ('мокрожопый'),
       ('мокропиздая'),
       ('моча'),
       ('мочиться'),
       ('мудак'),
       ('мудашвили'),
       ('мудила'),
       ('мудило'),
       ('мудильщик'),
       ('мудистый'),
       ('мудить'),
       ('мудоеб'),
       ('набздеть'),
       ('наблядоваться'),
       ('надристать'),
       ('надроченный'),
       ('надрочивать'),
       ('надрочить'),
       ('надрочиться'),
       ('наебанный'),
       ('наебать'),
       ('наебаться'),
       ('наебка'),
       ('наебнуть'),
       ('наебнуться'),
       ('наебщик'),
       ('наебывать'),
       ('наебываться'),
       ('наебыш'),
       ('накакать'),
       ('накакаться'),
       ('накакивать'),
       ('напиздить'),
       ('напиздошить'),
       ('напиздюрить'),
       ('напиздюриться'),
       ('насрать'),
       ('насраться'),
       ('нассать'),
       ('нассаться'),
       ('натрахать'),
       ('натрахаться'),
       ('натрахивать'),
       ('натрахиваться'),
       ('нахуякать'),
       ('нахуякаться'),
       ('нахуякивать'),
       ('нахуякиваться'),
       ('нахуяривать'),
       ('нахуяриваться'),
       ('нахуярить'),
       ('нахуяриться'),
       ('нахуячивать'),
       ('нахуячиваться'),
       ('нахуячить'),
       ('нахуячиться'),
       ('нахуяшить'),
       ('недоебанный'),
       ('недоносок'),
       ('неебущий'),
       ('нищеебство'),
       ('обдристанный'),
       ('обдристать'),
       ('обдрочиться'),
       ('обосранец'),
       ('обосранная'),
       ('обосраный'),
       ('обосрать'),
       ('обосраться'),
       ('обоссанец'),
       ('обоссаный'),
       ('обоссать'),
       ('обоссаться'),
       ('обоссывать'),
       ('обоссываться'),
       ('обпиздить'),
       ('обпиздиться'),
       ('обпиздовать'),
       ('обпиздоваться'),
       ('обпиздовывать'),
       ('обпиздовываться'),
       ('обпиздохать'),
       ('обпиздохаться'),
       ('обпиздохивать'),
       ('обпиздохиваться'),
       ('обпиздошить'),
       ('обтрахать'),
       ('обтрахаться'),
       ('обтрахивать'),
       ('обтрахиваться'),
       ('обхуярить'),
       ('обхуяриться'),
       ('обхуячить'),
       ('объебать'),
       ('объебаться'),
       ('объебенить'),
       ('объебешь'),
       ('объебнуть'),
       ('объебон'),
       ('одинхуй'),
       ('однапизда'),
       ('однохуйственно'),
       ('оебать'),
       ('оебашивать'),
       ('оебашить'),
       ('оебенивать'),
       ('оебенить'),
       ('оебыват'),
       ('опедерастить'),
       ('опизденеть'),
       ('опизденно'),
       ('опизденный'),
       ('опиздеть'),
       ('опиздить'),
       ('остоебенило'),
       ('остоебенить'),
       ('остоебеть'),
       ('остопиздело'),
       ('остопиздеть'),
       ('остохуело'),
       ('остохуеть'),
       ('отдрачивать'),
       ('отдрачиваться'),
       ('отдрочить'),
       ('отдрочиться'),
       ('отпиздить'),
       ('отпиздошить'),
       ('отпиздяшивание'),
       ('отпиздяшивать'),
       ('отпиздяшиваться'),
       ('отпиздяшить'),
       ('отпиздяшиться'),
       ('отсасывать'),
       ('отсасываться'),
       ('отсосать'),
       ('отсосаться'),
       ('оттраханная'),
       ('оттрахать'),
       ('оттрахаться'),
       ('оттрахивать'),
       ('оттрахиваться'),
       ('отхерачить'),
       ('отхуякать'),
       ('отхуякаться'),
       ('отхуякивать'),
       ('отхуякиваться'),
       ('отхуяривать'),
       ('отхуяриваться'),
       ('отхуярить'),
       ('отхуяриться'),
       ('отхуячивать'),
       ('отхуячиваться'),
       ('отхуячить'),
       ('отхуячиться'),
       ('отхуяшивать'),
       ('отхуяшиваться'),
       ('отхуяшить'),
       ('отхуяшиться'),
       ('отъебать'),
       ('отъебашивание'),
       ('отъебашивать'),
       ('отъебашиваться'),
       ('отъебашить'),
       ('отъебенивать'),
       ('отъебениваться'),
       ('отъебенить'),
       ('отъебениться'),
       ('отъебись'),
       ('отъебнуть'),
       ('отъебывание'),
       ('отъебывать'),
       ('отъебываться'),
       ('отьебаться'),
       ('отьебашиться'),
       ('отьебенивание'),
       ('отьебись'),
       ('отьебнуться'),
       ('охуевать'),
       ('охуевающий'),
       ('охуевший'),
       ('охуение'),
       ('охуенно'),
       ('охуенные'),
       ('охуеть'),
       ('охуительно'),
       ('охуительный'),
       ('охуякать'),
       ('охуякаться'),
       ('охуякивать'),
       ('охуякиваться'),
       ('охуякнуть'),
       ('охуякнуться'),
       ('охуяривать'),
       ('охуяриваться'),
       ('охуярить'),
       ('охуяриться'),
       ('охуячивать'),
       ('охуячиваться'),
       ('охуячить'),
       ('охуячиться'),
       ('охуяшивать'),
       ('охуяшиваться'),
       ('охуяшить'),
       ('охуяшиться'),
       ('очко'),
       ('падла'),
       ('падлюка'),
       ('педераст'),
       ('педерастина'),
       ('педерастический'),
       ('педерастия'),
       ('педик'),
       ('педрило'),
       ('пежить'),
       ('пенис'),
       ('пердеж'),
       ('пердеть'),
       ('перднуть'),
       ('пердун'),
       ('перебздеть'),
       ('передрачивать'),
       ('передрочить'),
       ('передрочиться'),
       ('переебаться'),
       ('переебашить'),
       ('перетрахать'),
       ('перетрахаться'),
       ('перетрахивать'),
       ('перетрахиваться'),
       ('перехуйнуть'),
       ('перехуйнуться'),
       ('перехуякать'),
       ('перехуякаться'),
       ('перехуякивать'),
       ('перехуякиваться'),
       ('перехуякнуть'),
       ('перехуякнуться'),
       ('перехуяривать'),
       ('перехуяриваться'),
       ('перехуярить'),
       ('перехуяриться'),
       ('перехуячивать'),
       ('перехуячить'),
       ('перехуячиться'),
       ('пидор'),
       ('пидорас'),
       ('пизда'),
       ('пизданутая'),
       ('пиздануть'),
       ('пиздануться'),
       ('пиздато'),
       ('пизденка'),
       ('пизденочка'),
       ('пизденыш'),
       ('пиздень'),
       ('пиздеть'),
       ('пиздец'),
       ('пиздища'),
       ('пиздобол'),
       ('пиздовать'),
       ('пиздолиз'),
       ('пиздомол'),
       ('пиздосос'),
       ('пиздоход'),
       ('пиздуй'),
       ('пиздун'),
       ('пиздюга'),
       ('пиздюк'),
       ('пиздюкать'),
       ('пиздюкаться'),
       ('пиздюлей'),
       ('пиздюли'),
       ('пиздюлина'),
       ('пиздюшка'),
       ('пиздякать'),
       ('пиздятина'),
       ('пиздятиной'),
       ('пиздячий'),
       ('писька'),
       ('писюлек'),
       ('плоскозадая'),
       ('поблудить'),
       ('поблядовать'),
       ('поблядушка'),
       ('подосрать'),
       ('подосраться'),
       ('подоссать'),
       ('подпиздить'),
       ('подпиздовать'),
       ('подпиздоваться'),
       ('подпиздовывать'),
       ('подпиздовываться'),
       ('подпиздохать'),
       ('подпиздохаться'),
       ('подпиздохивать'),
       ('подпиздохиваться'),
       ('подпиздошивать'),
       ('подпиздошить'),
       ('подпиздошиться'),
       ('подпиздякать'),
       ('подпиздякаться'),
       ('подпиздякивать'),
       ('подпиздякиваться'),
       ('подпиздяривать'),
       ('подпиздяриваться'),
       ('подпиздярить'),
       ('подпиздяриться'),
       ('подпиздяхать'),
       ('подпиздяхаться'),
       ('подпиздяхивать'),
       ('подпиздяхиваться'),
       ('подпиздячивать'),
       ('подпиздячиваться'),
       ('подпиздячить'),
       ('подпиздячиться'),
       ('подпиздяшивать'),
       ('подпиздяшиваться'),
       ('подпиздяшить'),
       ('подпиздяшиться'),
       ('подристывать'),
       ('подрочить'),
       ('подсирать'),
       ('подхуякать'),
       ('подхуякаться'),
       ('подхуякивать'),
       ('подхуякиваться'),
       ('подхуякнуть'),
       ('подхуякнуться'),
       ('подхуяривать'),
       ('подхуяриваться'),
       ('подхуярить'),
       ('подхуяриться'),
       ('подхуячивать'),
       ('подхуячиваться'),
       ('подхуячиться'),
       ('подхуяшивать'),
       ('подхуяшиваться'),
       ('подхуяшить'),
       ('подхуяшиться'),
       ('подъеб'),
       ('подъебать'),
       ('подъебаться'),
       ('подъебашить'),
       ('подъебка'),
       ('подъебнуть'),
       ('подъебывать'),
       ('подъябывать'),
       ('поебанный'),
       ('поебать'),
       ('поебаться'),
       ('поебень'),
       ('поебистика'),
       ('поебон'),
       ('поебончик'),
       ('поебочка'),
       ('поебывать'),
       ('поебываться'),
       ('попердеть'),
       ('попердеться'),
       ('попердывать'),
       ('попизденная'),
       ('попиздеть'),
       ('попиздистее'),
       ('попиздить'),
       ('попиздиться'),
       ('попиздоболивать'),
       ('попиздоболиваться'),
       ('попиздоболить'),
       ('попиздоболиться'),
       ('попиздоватей'),
       ('попиздовать'),
       ('попиздоваться'),
       ('попиздовывать'),
       ('попиздовываться'),
       ('попиздохать'),
       ('попиздохаться'),
       ('попиздохивать'),
       ('попиздохиваться'),
       ('попиздошивать'),
       ('попиздошиваться'),
       ('попиздошить'),
       ('попиздошиться'),
       ('попиздюкать'),
       ('попиздюкаться'),
       ('попиздюкивать'),
       ('попиздюкиваться'),
       ('попиздюливать'),
       ('попиздюливаться'),
       ('попиздюлить'),
       ('попиздюлиться'),
       ('попиздюривать'),
       ('попиздюриваться'),
       ('попиздюрить'),
       ('попиздюриться'),
       ('попиздюхать'),
       ('попиздюхаться'),
       ('попиздюхивать'),
       ('попиздюхиваться'),
       ('попиздякать'),
       ('попиздякаться'),
       ('попиздякивать'),
       ('попиздякиваться'),
       ('попиздяривать'),
       ('попиздяриваться'),
       ('попиздярить'),
       ('попиздяриться'),
       ('попиздяхать'),
       ('попиздяхаться'),
       ('попиздяхивать'),
       ('попиздяхиваться'),
       ('попиздячивать'),
       ('попиздячиваться'),
       ('попиздячить'),
       ('попиздячиться'),
       ('попиздяшивать'),
       ('попиздяшиваться'),
       ('попиздяшить'),
       ('попиздяшиться'),
       ('попизживать'),
       ('попизживаться'),
       ('потаскун'),
       ('потаскуха'),
       ('потраханная'),
       ('потрахать'),
       ('потрахаться'),
       ('потрахивать'),
       ('потрахиваться'),
       ('похер'),
       ('похуист'),
       ('похуякать'),
       ('похуякаться'),
       ('похуякивать'),
       ('похуякиваться'),
       ('похуяривать'),
       ('похуяриваться'),
       ('похуярить'),
       ('похуяриться'),
       ('похуячивать'),
       ('похуячиваться'),
       ('похуячить'),
       ('похуячиться'),
       ('похуяшивать'),
       ('похуяшиваться'),
       ('похуяшить'),
       ('похуяшиться'),
       ('поц'),
       ('пошмариться'),
       ('поябывать'),
       ('приебать'),
       ('приебаться'),
       ('приебашивать'),
       ('приебашиваться'),
       ('приебашить'),
       ('приебашиться'),
       ('приебенивать'),
       ('приебениваться'),
       ('приебенить'),
       ('приебениться'),
       ('приебехать'),
       ('приебехаться'),
       ('приебехивать'),
       ('приебехиваться'),
       ('приебистый'),
       ('приебуривать'),
       ('приебуриваться'),
       ('приебурить'),
       ('приебуриться'),
       ('приебывать'),
       ('приебываться'),
       ('прижопить'),
       ('прижопывать'),
       ('прикинуть'),
       ('примавдовывать'),
       ('примандехать'),
       ('примандехаться'),
       ('примандехивать'),
       ('примандехиваться'),
       ('примандить'),
       ('примандиться'),
       ('примандовать'),
       ('примандоваться'),
       ('примандовываться'),
       ('примандохать'),
       ('примандохаться'),
       ('примандохивать'),
       ('примандохиваться'),
       ('примандошивать'),
       ('примандошиваться'),
       ('примандошить'),
       ('примандошиться'),
       ('примандюкать'),
       ('примандюкаться'),
       ('примандюкивать'),
       ('примандюкиваться'),
       ('примандюливать'),
       ('примандюливаться'),
       ('примандюлить'),
       ('примандюлиться'),
       ('примандюривать'),
       ('примандюриваться'),
       ('примандюрить'),
       ('примандюриться'),
       ('примандякать'),
       ('примандякаться'),
       ('примандякивать'),
       ('примандякиваться'),
       ('примандяривать'),
       ('примандяриваться'),
       ('примандярить'),
       ('примандяриться'),
       ('примандяхать'),
       ('примандяхаться'),
       ('примандяхивать'),
       ('примандяхиваться'),
       ('примандячивать'),
       ('примандячиваться'),
       ('примандячить'),
       ('примандячиться'),
       ('примандяшивать'),
       ('примандяшиваться'),
       ('примандяшить'),
       ('примандяшиться'),
       ('примудохать'),
       ('примудохаться'),
       ('примудохивать'),
       ('примудохиваться'),
       ('припизденный'),
       ('припиздень'),
       ('припиздить'),
       ('припиздиться'),
       ('припиздовать'),
       ('припиздоваться'),
       ('припиздовывать'),
       ('припиздовываться'),
       ('припиздохать'),
       ('припиздохаться'),
       ('припиздохивать'),
       ('припиздохиваться'),
       ('припиздошивать'),
       ('припиздошиваться'),
       ('припиздошить'),
       ('припиздошиться'),
       ('припиздронивать'),
       ('припиздрониваться'),
       ('припиздронить'),
       ('припиздрониться'),
       ('припиздывать'),
       ('припиздываться'),
       ('припиздюкать'),
       ('припиздюкаться'),
       ('припиздюкивать'),
       ('припиздюкиваться'),
       ('припиздюливать'),
       ('припиздюливаться'),
       ('припиздюлить'),
       ('припиздюлиться'),
       ('припиздюривать'),
       ('припиздюриваться'),
       ('припиздюрить'),
       ('припиздюриться'),
       ('припиздюхать'),
       ('припиздюхаться'),
       ('припиздюхивать'),
       ('припиздюхиваться'),
       ('припиздякать'),
       ('припиздякаться'),
       ('припиздякивать'),
       ('припиздякиваться'),
       ('припиздяривать'),
       ('припиздяриваться'),
       ('припиздярить'),
       ('припиздяриться'),
       ('припиздяхать'),
       ('припиздяхаться'),
       ('припиздяхивать'),
       ('припиздяхиваться'),
       ('припиздячивать'),
       ('припиздячиваться'),
       ('припиздячить'),
       ('припиздячиться'),
       ('припиздяшивать'),
       ('припиздяшиваться'),
       ('припиздяшить'),
       ('припиздяшиться'),
       ('припизживать'),
       ('припизживаться'),
       ('притрахаться'),
       ('прихуеть'),
       ('прихуякать'),
       ('прихуякаться'),
       ('прихуякивать'),
       ('прихуякиваться'),
       ('прихуяривать'),
       ('прихуяриваться'),
       ('прихуярить'),
       ('прихуяриться'),
       ('прихуячивать'),
       ('прихуячиваться'),
       ('прихуячить'),
       ('прихуячиться'),
       ('прихуяшивать'),
       ('прихуяшиваться'),
       ('прихуяшить'),
       ('прихуяшиться'),
       ('проблядовать'),
       ('проблядушка'),
       ('проблядь'),
       ('продрачивать'),
       ('продрачиваться'),
       ('продрочить'),
       ('продрочиться'),
       ('проебать'),
       ('проебаться'),
       ('проебашивать'),
       ('проебашиваться'),
       ('проебашить'),
       ('проебашиться'),
       ('проебенить'),
       ('проебениться'),
       ('проебывать'),
       ('проебываться'),
       ('пропиздить'),
       ('пропиздиться'),
       ('пропиздоболивать'),
       ('пропиздоболиваться'),
       ('пропиздоболить'),
       ('пропиздоболиться'),
       ('пропиздовать'),
       ('пропиздоваться'),
       ('пропиздовывать'),
       ('пропиздовываться'),
       ('пропиздон'),
       ('пропиздохать'),
       ('пропиздохаться'),
       ('пропиздохивать'),
       ('пропиздохиваться'),
       ('пропиздошивать'),
       ('пропиздошиваться'),
       ('пропиздошить'),
       ('пропиздошиться'),
       ('пропиздюкать'),
       ('пропиздюкаться'),
       ('пропиздюкивать'),
       ('пропиздюкиваться'),
       ('пропиздюливать'),
       ('пропиздюливаться'),
       ('пропиздюлить'),
       ('пропиздюлиться'),
       ('пропиздюривать'),
       ('пропиздюриваться'),
       ('пропиздюрить'),
       ('пропиздюриться'),
       ('пропиздюхать'),
       ('пропиздюхаться'),
       ('пропиздюхивать'),
       ('пропиздюхиваться'),
       ('пропиздякать'),
       ('пропиздякаться'),
       ('пропиздякивать'),
       ('пропиздякиваться'),
       ('пропиздяривать'),
       ('пропиздяриваться'),
       ('пропиздярить'),
       ('пропиздяриться'),
       ('пропиздяхать'),
       ('пропиздяхивать'),
       ('пропиздяхиваться'),
       ('пропиздячивать'),
       ('пропиздячиваться'),
       ('пропиздячить'),
       ('пропиздячиться'),
       ('пропиздяшивать'),
       ('пропиздяшиваться'),
       ('пропиздяшить'),
       ('пропиздяшиться'),
       ('пропизживать'),
       ('пропизживаться'),
       ('прохуякать'),
       ('прохуякаться'),
       ('прохуякивать'),
       ('прохуякиваться'),
       ('прохуяривать'),
       ('прохуяриваться'),
       ('прохуярить'),
       ('прохуяриться'),
       ('прохуячивать'),
       ('прохуячиваться'),
       ('прохуячить'),
       ('прохуячиться'),
       ('прохуяшивать'),
       ('прохуяшиваться'),
       ('прохуяшить'),
       ('прохуяшиться'),
       ('разблядоваться'),
       ('раздрочить'),
       ('раздрочиться'),
       ('раззалупаться'),
       ('разнохуйственно'),
       ('разъебать'),
       ('разъебаться'),
       ('разъебашивать'),
       ('разъебашиваться'),
       ('разъебашить'),
       ('разъебашиться'),
       ('разъебенивать'),
       ('разъебениваться'),
       ('разъебенить'),
       ('разъебениться'),
       ('распиздить'),
       ('распиздиться'),
       ('распиздовать'),
       ('распиздоваться'),
       ('распиздовывать'),
       ('распиздовываться'),
       ('распиздон'),
       ('распиздохать'),
       ('распиздохаться'),
       ('распиздохивать'),
       ('распиздохиваться'),
       ('распиздошивать'),
       ('распиздошиваться'),
       ('распиздошить'),
       ('распиздошиться'),
       ('распиздяй'),
       ('расхуяривать'),
       ('расхуяриваться'),
       ('расхуярить'),
       ('расхуяриться'),
       ('расхуячивать'),
       ('расхуячиваться'),
       ('расхуячить'),
       ('расхуячиться'),
       ('сдрочить'),
       ('сестроеб'),
       ('сифилитик'),
       ('сифилюга'),
       ('скурвиться'),
       ('смандить'),
       ('смандиться'),
       ('спам'),
       ('сперматозавр'),
       ('спиздеть'),
       ('стерва'),
       ('стервоза'),
       ('сука'),
       ('суки'),
       ('сукин'),
       ('сукины'),
       ('суходрочка'),
       ('суходрочкой'),
       ('сучара'),
       ('сучий'),
       ('сучка'),
       ('сучье'),
       ('схуякать'),
       ('схуякаться'),
       ('схуякивать'),
       ('схуякиваться'),
       ('схуяривать'),
       ('схуяриваться'),
       ('схуярить'),
       ('схуяриться'),
       ('схуячивать'),
       ('схуячить'),
       ('схуячиться'),
       ('съебать'),
       ('съебаться'),
       ('съебашивать'),
       ('съебашиваться'),
       ('съебашить'),
       ('съебашиться'),
       ('съебенивать'),
       ('съебенить'),
       ('съебениться'),
       ('съебывать'),
       ('съебываться'),
       ('тварь'),
       ('толстожопый'),
       ('толстозадая'),
       ('торчило'),
       ('траханье'),
       ('трахать'),
       ('трахаться'),
       ('трахнуть'),
       ('трахнуться'),
       ('трепак'),
       ('триппер'),
       ('ублюдок'),
       ('уебать'),
       ('уебашивать'),
       ('уебашить'),
       ('уебенить'),
       ('уебище'),
       ('уебывать'),
       ('уебываться'),
       ('уебыш'),
       ('усраться'),
       ('усрачка'),
       ('уссать'),
       ('уссаться'),
       ('ухуякать'),
       ('ухуякаться'),
       ('ухуякивать'),
       ('ухуякиваться'),
       ('ухуяривать'),
       ('ухуяриваться'),
       ('ухуярить'),
       ('ухуяриться'),
       ('ухуячивать'),
       ('ухуячиваться'),
       ('ухуячить'),
       ('ухуячиться'),
       ('ухуяшивать'),
       ('ухуяшиваться'),
       ('ухуяшить'),
       ('ухуяшиться'),
       ('фаллос'),
       ('фекал'),
       ('фекалии'),
       ('фекалий'),
       ('хер'),
       ('херами'),
       ('херня'),
       ('херов'),
       ('херовина'),
       ('хрен'),
       ('хреново'),
       ('хреновое'),
       ('хреновый'),
       ('худоебина'),
       ('хуебень'),
       ('хуев'),
       ('хуева'),
       ('хуевато'),
       ('хуеватый'),
       ('хуевина'),
       ('хуево'),
       ('хуевый'),
       ('хуеглот'),
       ('хуегрыз'),
       ('хуедрыга'),
       ('хуек'),
       ('хуемудрие'),
       ('хуемыслие'),
       ('хуеньки'),
       ('хуеплет'),
       ('хуесос'),
       ('хуета'),
       ('хуетень'),
       ('хуец'),
       ('хуечек'),
       ('хуи'),
       ('хуила'),
       ('хуило'),
       ('хуиный'),
       ('хуистый'),
       ('хуишко'),
       ('хуище'),
       ('хуй'),
       ('хуйло'),
       ('хуйнуть'),
       ('хуйню'),
       ('хуйня'),
       ('хуйство'),
       ('хули'),
       ('хуюживать'),
       ('хуюживаться'),
       ('хуюжить'),
       ('хуюжиться'),
       ('хуюшки'),
       ('хуя'),
       ('хуяк'),
       ('хуякать'),
       ('хуями'),
       ('хуярить'),
       ('хуяриться'),
       ('хуястый'),
       ('хуячий'),
       ('хуячить'),
       ('хуячиться'),
       ('хуяшить'),
       ('целка'),
       ('целку'),
       ('целочка'),
       ('черножопые'),
       ('чернозадый'),
       ('член'),
       ('шалава'),
       ('шлюха'),
       ('шмара'),
       ('шмарить'),
       ('шмариться');

create index SWEAR_IDX on swear_word(word);