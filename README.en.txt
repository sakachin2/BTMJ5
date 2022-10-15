(UTF8 encoding)
*********************************************************************************************
Japanese style Mahjong game to play among your friends.
Using wireless, no specific server machine is required.
You can play alone with biginner level robots.
*****
I suppose you are familiar to rule and operation of Japanese style Mahjong.
From 1 to 4 players can play game.
Rule of "Yakitori"(prize for once won), "Wareme"(split wall prize)  and "Tobi"(bankrupt prize) is supported.
Suspend and resume is supported.
Any local "Yaku"(winning pattern) is acceptable
 because winner can claim the value of winning hand.
Evaluation of winning hand is optional for brain training.
Checking validity of winning hand is also optional,
so "Chombo" by mistaken Ron may occur like as real Mahjong game.
Device is required portrait width >=360 dip, tablet is recommended.
Bluetooth(legacy mode) and Wifi-Direct are supported for wireless connection.

Please send your suggestion or bug reports to mail:sakachin2@yahoo.co.jp

V1.25 : 2022/10/14
        .Android 12 compatible with Bluetooth permissions.
        .Add History print function.
        .Bugs(balance seet of 4-player game, response when connection is broken)

V1.25 : 2022/10/14
        .Bluetooth 연결 권한과 관련하여 Android12 대응
        .히스토리 인쇄 기능 추가
        .Bugs(4 명 플레이 게임의 이력 집계, 접속이 끊어졌을 때의 대응)

*********************************************************************************************
V1.25 : 2022/10/14
        .Android 12 compatible with Bluetooth permissions
        .Add history print function
        .Bugs.
         -When selecting a 4-player game, the aggregated history is aggregated for all games.
         -Profile ICON may overwarp stocks depending on the model
         -message loops when connection is lost

        .Bluetooth 연결 권한과 관련하여 Android12 대응
        .히스토리 인쇄 기능 추가
        .Bugs.
         -4인 플레이 게임을 선택했을 때, 이력의 집계가 모든 게임의 집약이 되어 버린다.
         -모델에 따라 프로파일 ICON이 스톡에 겹칠 수 있음.
         -연결이 끊어지면 메시지가 반복됩니다.

V1.24 : 2022/10/08
		.Put rules commonly used together into one page of dialog.
		.Support to display profile-Icon set by preference dialog.
		.Check application version consistency at connection between devices.
        .(Bugs) Rule synchronization fails with device on which this App was installed at first.

V1.24 : 2022/10/08
		.Put rules commonly used together into one page of dialog.
		.Support to display profile-Icon set by preference dialog.
		.Check application version consistency at connection between devices.
        .(Bugs) Rule synchronization fails with device on which this App was installed at first.

        .대표적인 규칙 설정 항목을 한 페이지로 집계.
        .개인 설정으로 프로필 아이콘 표시 지원.
        .연결할 때 앱 버전이 일치하는지 확인.
        .(결함) 첫 번째 설치의 경우 규칙 동기화가 실패합니다.

V1.24 : 2022/10/08
		.Put rules commonly used together into one page of dialog.
		.Provide some messages in .ko.
		.Adjust spacing between characters for vertical writing on landscape panel.
		.Support to display profile-Icon set by preference dialog.
		.Check application version consistency at connection between devices.
        .Bugs.
		 -Rule synchronization fails with device on which this App was installed at first.

V1.23 : 2022/08/25
        .Implemented "Determination of NagashiMangan", "8 consecutive win" and Psuedo-Tenpai.
		.Change of Open-Riichi option.
		 Player called Riich before Open-Riich is issued don't need to pay Yakuman.
		.Check inconsistency of APP version between connected devices.
        .Bugs.
		 -Furiten was not checked for Chankan against Add-Kan.
         -Missing check "_Fix _Yaku" error for "_Double-Run" and "3_conceiled_Triples".
		 -Around resume game from History record.

V1.23 : 2022/08/25 shorten
        .Implemented "Determination of NagashiMangan", "8 consecutive win" and Psuedo-Tenpai.
		.Change of Open-Riichi option.
		.Check inconsistency of APP version between connected devices.
        .Bugs.
		 -Furiten was not checked for Chankan against Add-Kan.
         -Missing check "_Fix _Yaku" error for "_Double-Run" and "3_conceiled_Triples".
		 -Around resume game from History record.

V1.23 : 2022/08/25
		."NagashiMangan", "8 consecutive win" 및 "Psuedo-Tenpai"의 결정을 구현했습니다.
        .Open-Riichi 옵션을 변경했습니다.
         Open-Riich가 발행되기 전에 Riich를 호출한 플레이어는 Yakuman을 지불할 필요가 없습니다.
		.Check inconsistency of APP version between connected devices.
        .연결된 장치 간의 APP 버전 불일치를 확인합니다.
        .Bugs.
         -Add-Kan(加槓)에 대한 Chanken(槍槓)의 동순 Furiten 체크를 하지 않았다.
         -Double-Run(一盃口)/3 conceiled Triples(三暗刻)의 "Fix Yaku" 룰 체크가 되어 있지 않았다.
         - 2-han 제약 조건에서 2차 명예 타일에 대한 "야쿠 수정" 오류 확인.
         -히스토리 레코드에서 게임 재개에 대해.

V1.23 : 2022/08/25    ( shorten)  
		."NagashiMangan", "8 consecutive win" 및 "Psuedo-Tenpai"의 결정을 구현했습니다.
        .Open-Riichi 옵션을 변경했습니다.
		.Check inconsistency of APP version between connected devices.
        .연결된 장치 간의 APP 버전 불일치를 확인합니다.
        .Bugs.
         -Add-Kan(加槓)에 대한 Chanken(槍槓)의 동순 Furiten 체크를 하지 않았다.
         -Double-Run(一盃口)/3 conceiled Triples(三暗刻)의 "Fix Yaku" 룰 체크가 되어 있지 않았다.
         - 2-han 제약 조건에서 2차 명예 타일에 대한 "야쿠 수정" 오류 확인.
         -히스토리 레코드에서 게임 재개에 대해.

V1.23 : 2022/08/25
        .Implemented "Determination of NagashiMangan" and "8 consecutive win".
		.Implemented Psuedo-Tenpai.
		."Disable":ON means win-able for other player's Discard.
		 "Disable":OFF means win-able by self-draw allowing Furiten, Kataagari and err by "Fix Yaku".
         Constraint check is optional.
		 Noten Riichi are possible, so Riichi itself is not a Tenpai condition.
		.Implemented Discarding for Open-Riichi. Opptionally issues warning.
		 Check for also Chankan against Add-Kan whether it is winning tile or not for Open-Riichi.
		 For Open-Riichi after your normal Riichi, your need not to pay Yakuman.
		.Open Riichi option for the game included Robot is changed to simply "Yes" or "No". Robot pays Yakuman if "Yes".
		.When "Check Furiten" option is set to OFF, the message for Furiten error is not displayed, andthe button notification is not done.
		 Change the default to ON.
		.Check inconsistency of APP version between connected devices.
        .Strengthen smart Robot.
		 -Skip Riichi if winning tile for 7-pair is Dora tile.
		 - Do not Riichi when Open-Riichi is issued.
		 - Check furiten against Open-Riichi and choose a Discard.
		 -7-Pairs do not discard Word tiles as much as possible when 1-Shanten.
		 -Avoid waiting for Honor tile for 7-Pairs.
        .Bugs.
		 -Didn't check Furiten of "in the same sequence" for Chankan against Add-Kan.
		 -Didn't check "Fix Yaku" violation for Double-Run/3 conceiled Triples.
		 -Didn't check "Fix Yaku" violation for 2'nd honor tile in 2-han constraint condition.
		 -Game including Robot may hung if "Win call is cancelable" option is ON.
		 -Around resume game from History record.
		  .Rule file used in the resumed game is used continuingly in the next game.
		  .Rule file used in the game resumed and gameoved is deleted.
		   It disturbs to resume the suspended games using the same rule file.
		  .Record resumed and gameovered is remains in the history.

V1.22 : 2022/07/16
		.Hungle support for Help and some Message dialog.
V1.21 : 2022/04/23
		.For Android-S(12):API31
        .Utilize animation for action Pon, Kan, Chii, ...
V1.21 : 2022/04/23
		.For Android-S(12):API31
        .Utilize animation for action Pon, Kan, Chii, ...
        .Text zooming on Help dialog.
        .Take a distance for button:Win-Anyway to avoid mistouching.
        .Gude to menu item usage when Gameover was rejected.
        .To avoid need of Rule-Sync by option change, move PlayAloneMode option to Preference from Oper settings.
        .Bugs
         -Japanese:"ありありなど" was shown on rule dialog.
         -Enabling Bluetooth loops on Emulator on Android studio.
         -In PlayAlone mode, correct error message for "Draw" button.
         -In NotifyMatchMode, Buttonn background color was not reset by draw by autotake timeout.

V1.20 : 2022/03/24
        .Inplements Furiten Riichi option.
        .inplements Abortive Draw 4Kans, 4Winds and 4Riichi.
        .Show changed option with different background color when received Rule setting.
        .Strengthen smart Robot and fix Bugs.
V1.20 : 2022/03/24
        .No 1-han constraint check for Win-Anyway.
        .Allow Kataagari if Fix Yaku option is finally, else apply setting  of self-drawn option.
        .Inplements Furiten Riichi option.
        .Specific error msg for Kataagari error and 1-han constraint error.
        .Display not only Wind but also player's name on Win claim and End-Of-Hand dialog.
        .Automatically open Abortive Draw dialog for 4Kans, 4Winds and 4Riichi. Accordingly drop Discard+Draw button.
        .Show changed option with different background color when received Rule setting.
        .Closed child dialog of Rule setting(Operation or Yaku dialog) if opened when received Rule setting.
        .Strengthen smart Robot.
         -Implements RinshanKaiho(King's Tile drawn).
        .Bugs
         -Decision of Fix Yaku:middle for 3 color runs and Straight.
         -Han for 3 dragon(White, Green and Red) triples may be counted duplicatedly.
         -Little Dragons have to be checked Fix-Yaku rule.
         -Game may hung when Abotive Draw dialog was canceled.
         -13 orphan could not be called for conceiled Kan call.
         -Robot's decision error for marking other playter which intent seems to be flush.
         -Exception may occur by error about decision of calling Chii with intent of Straight.
V1.19 : 2022/02/01
        .Show Furiten Riichi error.
        .Strengthen smart Robot.
        .Fix bugs.
V1.19 : 2022/02/01
        .Write Exception log to Logcat even for release version.
        .Use different background color for PlayAloneMode game on History dialog.
        .Add NoDump option to TestOption for parformance of AndroidStudio DebugRun.
        .Adjust text of player name on Nameplate to center.
        .Link to YouTube playlist:"How-To-Play e雀" from Help dialog of top panel.
        .Show Message of "Riichi called" at not called Riichi but discarded after Riichi.
        .Issue warning of Furiten Riichi if Furiten-Riichi option is not only Reject but also Yes.
        .Implements "You can not win even bay Draw for Furiten Riichi if the option is No".
        .Check Furiten Riichi for also Missing winning tile after Riichi. It is not winnable.
        .Show on dialog of Complete, ExaustiveDraw and HalfwayDrw the errof of Furiten Riich(if option=No) and Missing winning tile after Riichi.
        .Ankan will not be notified and AddKan is notified at timing of Draw the tile.
        .Change backgroundcolor of Connect button and Gametype textbox to identify device is server or client.
        .Strengthen smart Robot.
         -When otherReach ctr=1, skip call if dora ctr=0 even fixed1.
         -drop "once called" from call reason.
         -Do "Giveup Check" also before decision of Call or Not Call.
         -Not select tile to discard which cause Furiten error.
         -To determine Intent:Chanta Number tile 3 with no 1 and 2 and Number tile 7 with no 8 and 9 are considered as Tanyao tile.
         -Aim to higher point at near Final round.
         -Consider number of tiles of other color before call CHii with intent Same-Color.
         -Call Pon for honor tile Doubled-East if shanten>=2 with 7Pair intent.
         -If 7pair intent, mark Riichi player even shanten<=1.
         -When marking Riichi player, select tile to discard considering Honour tile's number and value.
         -For 7Pair, think important tile of near Red Dora for Suits and not Value for Honour.
         -For 7Pair, set more discardable for tile of Furiten itself and less discardable for tile of Suji(e.g 1 of 4, 2 fo 5..) and honours.
         -At Tenpai(shanten=0) of Tanyao 7Pair, wait Riichi until draw Tanyao tile if both tile is Chanta tile other than Dora.
         -At Tenpai(shanten=0) of Chanta 7Pair, wait Riichi until draw Chanta tile if both tile is Tanyao tile regardless Dora.
         -Set Intent:Chanta for 7Pair if Count of Chanta Piar > 4 (if pair=4, set if single Chanta Tile >=2)
         -Implement "Vlue Honour tile has 1 han even exposed if option of Sakiduke-rule is not 1st".
         -Skip call Kan for discarded if it violates Sakiduke-rule=1st.
         -At Tenpai(shanten=0) of (Half-)Samecolor 7Pair, wait Riichi until draw tile of the color or Honour tile.
        .Bugs
         -Japanese:NoUserBGM was shown on English env.
         -Function:Suspend was deleted, it was remain on Help of MenuInGame.
         -DrawnHW request had to be rejected before deal not only for 1st deal but all round.
         -KanDora was not considered at decision of Call or Not Call.
         -Try-Catch was required for function Onclick of Alert dialog.
         -Crash when AbotiveDrawn confirm dialog was duplicatedly shown.
         -Alert was issued also at before deal when AbortiveDrawn dialog was canceled.
         -Eception occured at Send on history dialog between SDCARD system and Scoped system.
         -When postioning mode is "Simply", keep lamp disabled to avoid double touch event.
         -The value of discard priority was doubled when conditionally Riichi was bypassed at discard.
         -Client player could not call Ron when match is not Notify mode.
         -You can not get win by Chankan(Ron for AddKan).
         -Client can puch "Game" button.
         -At start "Game", client causes NPE if server backed to top panel by the reason of client canceled orientation selection dialog. 
         -Background color of Point on nameplate of Client to identify Plus or Minus was different from Server.
V1.18 : 2021/11/26
        .Bug of mistaken meld of Chii.
        .Add setting option of "Reject Furiten Riichi"
V1.18 : 2021/11/26
        .On "#nd-Of-Hand" dialog, show total score as the result.
        .Add setting option of "Reject Furiten Riichi"
        .Bug of mistaken meld of Chii.

V1.17 : 2021/11/19
        .Deal with Google Console Crash Repoer(occures when App is destroyed and restart, restrt will not be supported).
        .Strengthen smart Robot, effectively use Pon/Chii.
        .Bugs
         -Kuikae("Violation o Same-Meld") may be issued mistakenly.
         -Misjudgement of Yaku for some tiles pattern.
V1.17 : 2021/11/19
        .Deal with Google Console Crash Repoer(occures when App is destroyed and restart, restrt will not be supported).
        .For Robot, accidental Yaku such as Chankan(Ron for Kan call) is out of 1 han constraint.
        .Strengthen smart Robot.
         -When aiming Big/Small-3Dragon, force Ppn/Chii call regarless Shanten-Down.
         -Avoid to call Riichi Pao condition is pending.
         -Consider possibility of 3-Same-Sequence/Straight/Tanyao/Chanta at Calling Pon/Chii and Selection of tile to discard.
         -Without possibility of 3-Same-Sequence/Straight/Tanyao/Chanta, Calling Pon/Chii depends  Honor-tile you have.
         -When calling Pon/Chii for 3-Same-Sequence/Straight, take Rule of Sakiduke(Timing of fix Yaku) into account.
         -Confirm to meet the condition of 1/2 han constraint rule when becomming Tenpai by the call Pon/Chii.
         -Confirm to meet the condition of 1/2 han constraint rule at 1st call.
         -Do not aim to Chanta if you have a Tanyao meld.
         -If Yaku is fixed by Honor tile, call Pon/Chii considering Dora tile counter you have.
         -For Big/Little 3-Dragon, add the condition of one triplet from White/Green/Red, avoid to call Kan and ignore to aim Chanta.
         -When aiming Flush, condition whether to call or not at eraly timing depends to the count of Dora of not all but of that color.
         -At early timing of the round, determine to aim to Flush if shanten<=3.
         -Avoid Chii when aiming to ToiToi(All Triplet).
         -Avoid to call Kan to number tile if all-in-hand and avoid to call Kan if other player called Riichi already.
         -When aiming Flush, force Pon/Chii regardless Shnten-Down, and Chii has priority over Pon if both possible.
         -For aiming ToiToi(All Triplet), add the condition of one triplet you have.
         -For aiming Flush, add the condition of no Meld of other color.
         -Force call Kan at taken if you have already fixed 1 han by Honor tile or all-in-hand status.
         -Robot will call Chankan(Ron call for the tile called Kan).
         -Refrain fron call Pon if there is possibility of 4 Anko(Conceiled triplet)
         -Select Reacher's discarded itself if giveup by short remaining tile count.
         -For aiming Chanta, take tile counter of 4/5/6 into account.
         -For aiming Tanyao, take honor tile meld into account.
         -For aiming Flush, take into account whether the honor tile's count is one or multiple.

        .Bugs
         -On Client device, it could not back to top panel by Back button if connection failure occured at before dealing tiles.
         -Message line on top panel may overflow to 2nd line.
         -Kuikae("Violation o Same-Meld") may be issued mistakenly.
         -Checking Dora tile opened was delayed to the first discard timing, it impacts to the determination about whether Robot should call Pon/Chii or not.
         -Earth melds was not considered to determination of aiming Chanta or Tanyao.
         -Miss Yaku of Chanta when 1/9 number tile is the pillow.
         -Misjudgement of Yaku may occurs if Chii was called.
         -Shanten calculation error in some case, it is bad effect to selection of tile to discard.
         -Ankan(Conceiled-Kan) did not display Red5-tile.
         -Count of Dora on Earth was not considered to determine about whether Robot should call Pon/Chii,
         -Robot's Chankan-Ron was notified under the Sakiduke-Rule.
         -Notify-Pon appears and remains on next round if the tile is also winning tile of Robot's win.
         -Misjudge as Mixed Chanta even the head is Tanyao tile.
         -Misjudge Yaku using not whole but partial meld.
         -Did not say Furiten when the winning tile is all exposed.
         -In Japanese environment, OrderPrize applyed 1 ran up over rule setting.
         -option to keep tile on river called, no shade on client.

V1.16 : 2021/10/05
        .On Preference you can set any music in your device as BGM.
		. Panel layout for tall phone device .
		.For Android-R(11)
    		External storage usage was changed at Android-11.
			At (re-)installation, it is required to specify a folder to save game data.
			Select "eMahjong" on the dialog shown next.
			Make the folder on the dialog if not exists.
V1.16 : 2021/10/05
        .On Preference, flip game buttons holizontaly for lefty.
        .On Preference, you can set any music in your device as BGM.
		.add function to Histor B/S dialog to show detail of games by the same members.
		.For Android-R(11)
    		External storage usage was changed at Android-11.
			At (re-)installation, it is required to specify a folder to save game data.
			Select "eMahjong" on the dialog shown next.
			Make the folder on the dialog if not exists.
			At 1st re-instalation of eMahjong only, old version data(history data and rule file) will be migrated to the folder.
		. delete sg.rulefile(rule fule at interrupted) at the game completed.
		. Panel layout for tall phone device .
		. (Bug)Colud not play alone if not update on rule dialog.
*********************************************************************************************
V1.15 : 2021/08/23
        .Correspond to narrow phone device with dpi over 360dp.
        .Bugs
         -Win-Anyway was allowed at before other player discards after drawn.
*********************************************************************************************
V1.14 : 2021/08/02
        .Add unit=0.5 seconds to setting option of DelayTime. Default was also changed from two to one second.
        .Optionally notify possibility of Pon/Kan/Chii/Ron.
        .For also PlayAlone mode, check violation of Rule of Furiten/Fix Yaku at first only/multiple Ron tile missing Yaku.
        .For also PlayAlone mode, apply option of "Ron is cancelable".
        .For also PlayAlone mode, a Win button push dose both "Accept Notify" and "Confirmed Win".
        .Change display sequence of Yaku on Win confirm dialog such as Yaku, Honor tile, Dora.
        .Concealed Kan and Added Kan are not notified from now.
        .Robot's dead hand option goes out of use.
        .Strengthen smart Robot.
         -Call Pon/Chii if Honor tile + Dora >=2
         -Select tile to discard to make higher Rank of Yaku if score is same.
         -Skip call Kan under other player's Riichi.
         -Continue up to 10 draws when Ron tile is Kanchan(center of sequence meld).
         -Correct Toitoi(All triples) intent decision.
         -Call Pon/Chii by high possibility of ToiToi and (Half-)Flush.
         -Select Word tile as Ron tile if rank and remaining tile count is same.
         -Skip Riichi when Yakuman Tenpai(status of waiting Winning tile).
         -For 7 pairs, consider possibility of Tanyao, Chanta and (Half-)Flush.
         -Call Pon/Chii at Shanten=1 if called once.
         -Call Chii for Penchan/Kanchan at Shanten=2.
         -Call Pon for discarded Dora considering Yaku Fix.
         -Use Red5 Dora if available for meld opened by Chii.
         -Avoid Riichi after anyone called Open-Riichi.
         -Wait to call Riichi in early phase if win form is Kanchan(middle tile of seq meld).
         -Yaku-Intent will not be changed after once called Pon/Chii.
         -For Intent:All-Trples, differentiate value of tile for triplet and pair.
         -Avoid selection of Intent:Tanyao if any Chanta Meld exists. 
         -At near the final round, call Pon for 1st discard of honor tile if the Robot is in the top place.
         -At near the final round, prioritize aimming Yaku decided over call Pon for 1st discard of honor tile.
         -All triples is preferable over 7 pairs.
         -Intent:7 pair is not selected if 2 sequence meld exist.
         -Avoid Riichi in early phase for more win possibility if winning tile is any of 2 pairs.
         -If intent:Flush is set, avoid call Chii for other color meld.
         -Call Pon for 1st discard of honor tile if GrillBird option is on inEast only round.
        .Bugs
         -Kan is not notified in PlayAlone mode.
         -In PlayAlone mode, call Ron of 13-broken/14-broken is rejected by not form of win.
         -Call Kan at 1st draw cause error.
         -Setting option of "Open Dora at MinKan" was ignored.
         -When Shanten < 2, Tanyao possibility was not considered.
         -Dora was ignored at Double-Riichi.
         -Discarded tile just before misleaded Furiten check.
         -Counted red-5 Dora at evaluate winning score at discard tile selection even it is to be discarded.
         -Decision error of Mixed-Outside-Hand.
         -Client Win message may be overtaken by Robot's Draw+Discard, it cause hung.
         -Chankan(Robbing a Kan) was not notified.
         -Could not win by Chankan.
         -Hung by Concealed Kan call if option of Kokushi(13 orphan) can call win for Concealed Kan is No.
         -RinshanKaiho(win by Kan-Draw at concealed Kan) was not notified.
         -Could not discard if canceled Win at Play-Alone notify mode.
         -At Robot selecting tile to discard, consider Dora for winning pattern expecting only when Yaku constraint is fixed.
         -Missing evaluation of Earth for decision of Intent:Chanta. 
         -Win by draw was not notified after issed Riichi.
V1.13 : 2021/06/21
        .Apply "Yakitori"(Grilled Bird) option to smart robot game.
        .Apply "Fixed Yaku" rule. (See Help of Settings-->Yaku)
         Reject Win call if violation detected.
        .Allow Riichi call for the case winning tile is empty.
        .At Riichi called, check Furiten and 2-han constraint rule.
         (You can force Riichi from Menu item "Riichi Anyway" after when error detected).
        .Support reverse orientation of device.
        .(BUG)
         -erroneous calculation of "Fu" for triplet taken.
         -e.g. 111222333 is not 3 conceiled treples but may be higher rank of Pure outside hand.
         -Trouble for "Tenho"(Win at fisrt take of dealer).
         -"Riichi" is ignored when RedDora and Tenpai Check option are both ON.
         -Exchanging Match and PlayAlone mode may requires APP restart.
V1.12 : 2021/05/06
        .Return to top panel by back btn when gameover of playing alone.
        .Move settion of "show RonAnyWay button" from preference to operation rule.
        .Show B/S also limited to a group.
		.At human take in PlayAlone notify mode, notify ron-able after chcking 1/2 han constraint.
		.Check 2 han constraint rule for human in PlayAloneNotify mode and Robot.
		.Now accidental Yaku such as Reach-OneShot is not counted for 2 han constraint.
		.Check Multi-Wait rule and Furiten for human in PlayAloneNotify mode and for Robot if not self-pick.
		.Now disallow that Multi-Wait OK when draw in rule of NOT "Fix Last".
		.Robot avoids Pon for Yaku Word tile when 2 han constraint mode.
		.reset Intent:GiveUp when reached to Tenpai.
		.reset Intent:GiveUp when reached to Tenpai.
		.Allow to double-don alos by robot.
        .(BUG)
         -"Not ron-able pattern" may issued at Tenho or Chiho call.
		 -Robot looses envaironment yaku:rinshan,hitei,hotei
         -Robot will not win by "13 broken"/"14 broken".
         -Robot looses intent of Big(Small) 3 Dragon.
         -2 han of Double Riichi Robot was not considerfed fr 2 han constraint check.
         -Robot miss calling Chii when Intent:SameColor.
         -Point calculation miss for Pinfu+SelfDraw.
         -Robot miss to call Chii by error of ShantenUp check.
         -When Double-Ron of Robot and Human, Robot overtook Human Ron process.
         -Start of furiten check tile is not at draw but at discard.
         -Msg "Violation of Same-Meld" may be issued at Chii.
V1.11 : 2021/04/09
        .Add option of notify mode at playing alone(notify availablity of calling Ron, Pon, Kan, Chii) for quick robot action.
        .Allow Double-Ron of Human and Robot at playing alone (At match game, human's Ron intervenes robot's Ron)
        .Add function of showing point summarized by each players.
        .Bug fixes.
         -When Double-Ron for tile calling Riichi, it shows standing and lying ovrrupped and loose Ron-color on nameplate. 
         -When Double-Ron of human and robot at playing alone, only one win call dialog is shown. 
         -Hung when robot's hand is not ronable at take by 1 han constrint.
         -Open Riichi Robot option is ignored on Yaku setting dilaog.
         -Misjudgement of 3shiki(3 color straight) when including 1 identical sequence.
         -Loose Yaku:reach for Ron for tile other players discarded with reach call.
V1.10 : 2021/03/20
        .Robots aim to win.
        .Play alone mode was added.
        .Bug fixes.
         -Exception occurs whe BGM stopped.
         -Misjudgment of Yaku:mixed-flush.
         -Could not call Ron for last tile on river.
         -Misjudgement of Tobi(Bunkrupt) for game with robots.
         -Loose Yaku:reach for Ron for tile other players discarded with reach call.
V1.09 : 2020/11/21
        .Handle Android10 deprecation.
        .Fix bugs about connection failure handling.
V1.08 : 2020/11/07
        .install trouble by progurd
V1.04 : 2020/11/05
        .Setting option of "Confirm Tenpai at Riichi" is added. 
        .(Bug)There were mistake about judgement of Yaku "13 unconnected", "Straight" and "All Runs".
        .(Bug)"Open Riichi" was not evaluated as 1 han.
V1.03 : 2020/10/19
        .Add option to check "1 han constraint" (confirm the Hand having 1-han at least) 
         and "Evaluate point by App" (App calculates score by "fu"(point) and "han"(rank) of the hand).
        .Optionally checks "Call the Same Meld" violation.
        .(Bug)7pair including quads was not allowed.
        .(Bug)Problem may occur at connection just after Your ID was changed on connection dialog.
V1.02 : 2020/05/09
        .(Bug)Reconnection fails if rule was changed at application restart.
        .BGM option added.
V1.01 : 2020/04/11 (1st release)
