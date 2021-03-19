(UTF8 encoding)
*********************************************************************************************
Japanese style Mahjong game to play among your friends.
Using wireless, no specific server machine is required.
You can play alone with biginner level robots.
*****
I suppose you are familiar to rule and operation of Japanese style Mahjong.
From 1 to 4 players can play game.
Rule of "Yakitori", "Wareme" and "Tobi" is supported.
Suspend and resume is supported.
Any local "Yaku"(winning pattern) is acceptable
 because winner can claim the value of winning hand.
Evaluation of winning hand is optional for brain training.
Checking validity of winning hand is also optional,
so "Chombo" by mistaken Ron may occur like as real Mahjong game.

Device is required portrait width 600 pixels over and 800 pixels is recommended.
Bluetooth(legacy mode) and Wifi-Direct are supported for wireless connection.

Please send your suggestion or bug reports to mail:sakachin2@yahoo.co.jp

V1.10 : 2021/03/20
        .Robots aim to win.
        .Play alone mode was added.
        .Bug fixes.
         -Exception occurs whe BGM stopped.
         -Misjudgment of Yaku:mixed-flush.
         -Could not call Ron for last tile on river.
         -Misjudgement of Tobi(Bunkrupt) for game with robots.
         -Loose Yaku:reach for Ron for tile other players discarded with reach call.
*********************************************************************************************
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
