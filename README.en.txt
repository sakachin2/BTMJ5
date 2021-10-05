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

V1.16 : 2021/10/05
        .On Preference you can set any music in your device as BGM.
		. Panel layout for tall phone device .
		.For Android-R(11)
    		External storage usage was changed at Android-11.
			At (re-)installation, it is required to specify a folder to save game data.
			Select "eMahjong" on the dialog shown next.
			Make the folder on the dialog if not exists.
*********************************************************************************************
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
