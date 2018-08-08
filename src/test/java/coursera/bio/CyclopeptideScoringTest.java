package coursera.bio;

import static coursera.bio.CyclopeptideScoring.score;
import static coursera.bio.Utils.stringToInts;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CyclopeptideScoringTest {

	@Test
	public void score() {
		List<Integer> spec = Arrays.asList(0, 99, 113, 114, 128, 227, 257, 299, 355, 356, 370, 371, 484);
		assertEquals(Integer.valueOf(11), score.apply("NQEL", spec));
		
		spec = stringToInts.apply("0 71 87 87 87 87 97 97 101 101 103 103 103 103 113 113 113 113 113 113 113 114 114 114 128 129 129 131 131 131 147 156 163 163 174 184 186 186 190 190 200 200 210 211 214 214 215 217 218 218 225 226 226 227 230 232 242 244 250 250 250 259 260 261 262 269 277 287 287 287 292 293 297 300 300 312 314 314 317 324 327 327 328 337 338 339 343 347 349 363 364 364 370 372 373 374 375 381 381 388 390 395 400 401 401 404 406 410 413 416 417 425 425 431 440 440 450 452 461 461 462 463 468 468 473 474 475 477 477 483 487 501 504 504 504 509 510 511 512 513 514 518 529 530 538 539 551 553 564 564 567 572 574 574 575 578 586 590 591 592 594 596 599 599 600 602 605 607 611 614 624 625 638 643 643 651 652 654 660 661 664 665 675 677 678 681 681 685 687 688 692 692 695 699 701 703 709 712 715 723 725 727 730 739 740 754 754 756 764 764 765 768 774 778 780 785 788 789 789 791 791 799 800 801 804 810 812 814 825 826 826 828 838 841 843 844 851 851 853 855 867 868 878 878 879 883 883 886 887 892 899 901 902 902 904 905 913 914 916 917 931 938 938 939 941 942 954 955 964 970 972 973 979 981 982 984 985 986 989 991 992 992 999 1012 1014 1015 1016 1017 1018 1025 1029 1030 1041 1051 1051 1060 1062 1065 1068 1068 1069 1073 1073 1073 1076 1076 1082 1086 1088 1095 1102 1104 1105 1105 1112 1113 1115 1115 1118 1128 1128 1138 1143 1153 1155 1158 1163 1164 1164 1170 1175 1176 1178 1179 1181 1182 1189 1189 1191 1193 1196 1199 1202 1202 1202 1215 1216 1218 1231 1232 1242 1243 1250 1251 1259 1259 1265 1266 1268 1272 1273 1277 1278 1283 1284 1289 1291 1292 1294 1299 1303 1303 1305 1306 1309 1312 1318 1319 1322 1328 1330 1330 1345 1355 1363 1363 1364 1365 1365 1373 1373 1378 1378 1379 1387 1387 1392 1396 1400 1402 1402 1404 1406 1417 1422 1425 1431 1432 1432 1435 1435 1443 1445 1446 1450 1452 1459 1465 1468 1475 1476 1476 1477 1479 1486 1489 1490 1491 1492 1493 1503 1509 1516 1529 1530 1533 1534 1535 1536 1538 1545 1559 1559 1559 1562 1563 1564 1565 1578 1578 1580 1580 1581 1582 1583 1589 1589 1590 1590 1590 1592 1593 1606 1619 1622 1632 1637 1642 1646 1647 1663 1664 1665 1665 1667 1667 1672 1672 1673 1676 1677 1679 1679 1690 1693 1693 1693 1693 1694 1694 1702 1703 1706 1709 1711 1719 1722 1734 1745 1750 1759 1764 1766 1768 1768 1769 1773 1776 1777 1780 1780 1782 1785 1786 1793 1795 1796 1796 1798 1803 1803 1807 1807 1816 1822 1823 1832 1836 1837 1840 1851 1856 1862 1865 1872 1876 1877 1879 1883 1883 1889 1890 1893 1894 1896 1897 1899 1904 1908 1908 1911 1916 1917 1924 1927 1929 1935 1936 1943 1943 1949 1954 1959 1959 1963 1965 1965 1966 1979 1984 1986 1990 1991 1996 2002 2003 2006 2006 2007 2011 2021 2021 2030 2030 2037 2037 2042 2043 2046 2046 2046 2057 2058 2062 2066 2068 2072 2074 2078 2082 2083 2094 2094 2094 2097 2098 2103 2103 2110 2110 2119 2119 2129 2133 2134 2134 2137 2138 2144 2149 2150 2154 2156 2161 2174 2175 2175 2177 2181 2181 2186 2191 2197 2197 2204 2205 2211 2213 2216 2223 2224 2229 2232 2232 2236 2241 2243 2244 2246 2247 2250 2251 2257 2257 2261 2263 2264 2268 2275 2278 2284 2289 2300 2303 2304 2308 2317 2318 2324 2333 2333 2337 2337 2342 2344 2344 2345 2347 2354 2355 2358 2360 2360 2363 2364 2367 2371 2372 2372 2374 2376 2381 2390 2395 2406 2418 2421 2429 2431 2434 2437 2438 2446 2446 2447 2447 2447 2447 2450 2461 2461 2463 2464 2467 2468 2468 2473 2473 2475 2475 2476 2477 2493 2494 2498 2503 2508 2518 2521 2534 2547 2548 2550 2550 2550 2551 2551 2557 2558 2559 2560 2560 2562 2562 2575 2576 2577 2578 2581 2581 2581 2595 2602 2604 2605 2606 2607 2610 2611 2624 2631 2637 2647 2648 2649 2650 2651 2654 2661 2663 2664 2664 2665 2672 2675 2681 2688 2690 2694 2695 2697 2705 2705 2708 2708 2709 2715 2718 2723 2734 2736 2738 2738 2740 2744 2748 2753 2753 2761 2762 2762 2767 2767 2775 2775 2776 2777 2777 2785 2795 2810 2810 2812 2818 2821 2822 2828 2831 2834 2835 2837 2837 2841 2846 2848 2849 2851 2856 2857 2862 2863 2867 2868 2872 2874 2875 2881 2881 2889 2890 2897 2898 2908 2909 2922 2924 2925 2938 2938 2938 2941 2944 2947 2949 2951 2951 2958 2959 2961 2962 2964 2965 2970 2976 2976 2977 2982 2985 2987 2997 3002 3012 3012 3022 3025 3025 3027 3028 3035 3035 3036 3038 3045 3052 3054 3058 3064 3064 3067 3067 3067 3071 3072 3072 3075 3078 3080 3089 3089 3099 3110 3111 3115 3122 3123 3124 3125 3126 3128 3141 3148 3148 3149 3151 3154 3155 3156 3158 3159 3161 3167 3168 3170 3176 3185 3186 3198 3199 3201 3202 3202 3209 3223 3224 3226 3227 3235 3236 3238 3238 3239 3241 3248 3253 3254 3257 3257 3261 3262 3262 3272 3273 3285 3287 3289 3289 3296 3297 3299 3302 3312 3314 3314 3315 3326 3328 3330 3336 3339 3340 3341 3349 3349 3351 3351 3352 3355 3360 3362 3366 3372 3375 3376 3376 3384 3386 3386 3400 3401 3410 3413 3415 3417 3425 3428 3431 3437 3439 3441 3445 3448 3448 3452 3453 3455 3459 3459 3462 3463 3465 3475 3476 3479 3480 3486 3488 3489 3497 3497 3502 3515 3516 3526 3529 3533 3535 3538 3540 3541 3541 3544 3546 3548 3549 3550 3554 3562 3565 3566 3566 3568 3573 3576 3576 3587 3589 3601 3602 3610 3611 3622 3626 3627 3628 3629 3630 3631 3636 3636 3636 3639 3653 3657 3663 3663 3665 3666 3667 3672 3672 3677 3678 3679 3679 3688 3690 3700 3700 3709 3715 3715 3723 3724 3727 3730 3734 3736 3739 3739 3740 3745 3750 3752 3759 3759 3765 3766 3767 3768 3770 3776 3776 3777 3791 3793 3797 3801 3802 3803 3812 3813 3813 3816 3823 3826 3826 3828 3840 3840 3843 3847 3848 3853 3853 3853 3863 3871 3878 3879 3880 3881 3890 3890 3890 3896 3898 3908 3910 3913 3914 3914 3915 3922 3922 3923 3925 3926 3926 3929 3930 3940 3940 3950 3950 3954 3954 3956 3966 3977 3977 3984 3993 4009 4009 4009 4011 4011 4012 4026 4026 4026 4027 4027 4027 4027 4027 4027 4027 4037 4037 4037 4037 4039 4039 4043 4043 4053 4053 4053 4053 4069 4140");
		assertEquals(Integer.valueOf(343), score.apply("MIFTWNLPMSLFCNDCSCALEHTLRCEYNWMSYSM", spec));
	}

	@Test
	public void scoreExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("CyclicScoring.txt");
		List<Integer> spec = stringToInts.apply(lines.get(2));
		String peptide = lines.get(1);
		assertEquals(Integer.valueOf(lines.get(4)), score.apply(peptide, spec));
	}

	@Test
	public void scoreDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_102_3.txt");
		List<Integer> spec = stringToInts.apply(lines.get(1));
		String peptide = lines.get(0);
		assertEquals(Integer.valueOf(518), score.apply(peptide, spec));
	}

	@Test
	public void quiz() {
		List<Integer> spec = Arrays.asList(0, 71, 178, 202, 202, 202, 333, 333, 333, 404, 507, 507);
		System.out.println(score.apply("MAMA", spec));
	}
}