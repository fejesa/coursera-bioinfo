package coursera.bio;

import static coursera.bio.LinearSpectrum.linearSpectrum;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class LinearSpectrumTest {

	@Test
	public void spectrum() {
		List<Integer> expected = Arrays.asList(0, 113, 114, 128, 129, 242, 242,	257, 370, 371, 484);
		assertEquals(expected, linearSpectrum.apply("NQEL"));
		
		expected = Arrays.asList(0, 99, 128, 147, 227, 275, 374);
		assertEquals(expected, linearSpectrum.apply("VKF"));

		expected = Arrays.asList(0, 99, 128, 163, 227, 291, 390);
		assertEquals(expected, linearSpectrum.apply("VKY"));
	}

	@Test
	public void spectrumExtraData() throws Exception {
		List<String> lines = FileUtil.loadFile("LinearSpectrum.txt");
		String expected = lines.get(3).trim();
		String result = linearSpectrum.apply(lines.get(1))
				.stream()
				.map(m -> Integer.toString(m))
				.collect(Collectors.joining(" "));
		assertEquals(expected, result);
	}

	@Test
	public void spectrumDataset() throws Exception {
		List<String> lines = FileUtil.loadFile("dataset_4912_2.txt");
		String result = linearSpectrum.apply(lines.get(0))
				.stream()
				.map(m -> Integer.toString(m))
				.collect(Collectors.joining(" "));
		String expected = "0 57 71 71 87 87 97 97 97 101 103 113 113 113 113 113 113 114 114 114 114 115 115 128 128 128 128 128 128 128 129 129 129 131 131 131 131 137 147 163 163 163 163 163 163 163 184 184 184 199 200 200 202 211 215 220 220 225 226 227 228 229 240 241 241 242 242 242 242 243 243 244 244 244 245 245 245 245 246 258 260 264 265 266 271 275 291 291 292 297 298 312 313 315 315 321 326 326 326 328 329 333 339 342 348 356 357 357 358 358 359 359 368 369 371 371 371 372 372 373 373 374 376 378 383 384 386 388 389 392 403 426 426 428 428 429 429 429 435 444 446 446 449 454 455 457 458 471 472 473 473 484 484 484 484 485 485 486 488 489 489 490 491 499 500 502 502 511 515 516 517 520 531 531 540 542 543 546 548 552 557 557 557 557 559 566 571 575 582 586 587 592 598 598 601 602 603 612 612 612 613 613 615 617 617 618 620 628 629 630 630 630 631 634 636 670 670 671 671 671 674 677 683 688 693 694 694 695 699 699 700 702 704 709 711 714 715 716 721 726 728 729 730 740 741 741 741 743 744 745 749 757 759 759 761 764 765 771 784 785 797 802 802 806 812 817 817 817 818 821 823 827 828 829 830 837 840 842 846 854 855 856 856 856 857 857 858 858 862 872 872 873 874 875 877 879 888 899 900 904 915 916 916 919 924 928 930 934 940 941 941 943 945 949 955 957 960 968 969 970 970 984 984 984 986 986 986 988 992 993 1001 1001 1003 1003 1005 1013 1017 1020 1028 1029 1032 1037 1042 1047 1050 1055 1056 1062 1063 1063 1067 1069 1070 1071 1074 1082 1083 1085 1086 1088 1091 1097 1098 1099 1100 1108 1112 1114 1116 1117 1120 1132 1133 1142 1144 1145 1149 1155 1160 1165 1166 1170 1177 1183 1184 1186 1187 1187 1191 1194 1195 1195 1198 1199 1199 1200 1201 1205 1213 1213 1214 1216 1225 1225 1226 1230 1231 1245 1245 1246 1246 1248 1252 1257 1273 1295 1296 1300 1300 1301 1301 1305 1305 1308 1308 1311 1312 1312 1313 1314 1316 1318 1328 1328 1328 1329 1331 1332 1340 1344 1353 1354 1358 1359 1362 1365 1374 1377 1379 1386 1389 1408 1414 1415 1415 1419 1421 1425 1429 1429 1431 1432 1436 1436 1440 1441 1441 1442 1442 1445 1445 1446 1457 1459 1459 1459 1460 1468 1468 1468 1487 1491 1492 1492 1516 1516 1517 1528 1528 1539 1543 1545 1546 1546 1549 1549 1553 1554 1555 1555 1557 1558 1558 1558 1570 1570 1573 1574 1574 1582 1596 1599 1599 1620 1620 1622 1622 1623 1629 1629 1631 1643 1644 1645 1654 1657 1659 1668 1671 1672 1674 1677 1679 1679 1680 1682 1683 1683 1683 1686 1686 1700 1702 1710 1712 1712 1733 1734 1736 1737 1741 1757 1757 1757 1758 1762 1772 1773 1774 1783 1785 1788 1790 1794 1796 1796 1797 1797 1801 1803 1807 1811 1813 1814 1820 1828 1842 1843 1849 1854 1865 1865 1871 1872 1875 1885 1886 1887 1888 1894 1896 1903 1910 1916 1917 1919 1920 1922 1924 1925 1925 1926 1927 1929 1935 1936 1941 1944 1946 1957 1970 1978 1983 1993 1999 2000 2001 2002 2003 2007 2016 2016 2016 2030 2038 2039 2039 2042 2048 2048 2049 2049 2054 2055 2057 2058 2059 2066 2072 2072 2085 2098 2106 2115 2117 2129 2129 2130 2131 2136 2145 2145 2146 2156 2161 2162 2162 2163 2167 2168 2169 2170 2170 2170 2179 2185 2186 2186 2186 2200 2220 2229 2242 2243 2245 2248 2249 2257 2257 2258 2261 2265 2269 2274 2281 2283 2285 2293 2294 2298 2299 2299 2308 2313 2313 2314 2314 2316 2342 2345 2351 2364 2370 2370 2372 2376 2378 2383 2385 2386 2387 2392 2406 2408 2411 2412 2413 2426 2427 2428 2430 2437 2441 2444 2465 2471 2473 2474 2477 2482 2484 2485 2495 2498 2500 2501 2505 2509 2514 2514 2527 2539 2541 2550 2554 2556 2557 2558 2558 2590 2595 2596 2600 2602 2608 2612 2613 2614 2615 2624 2628 2629 2629 2632 2634 2636 2637 2669 2671 2672 2677 2684 2685 2686 2693 2709 2711 2713 2727 2729 2737 2742 2743 2745 2757 2759 2760 2763 2765 2765 2787 2792 2797 2798 2799 2799 2800 2800 2822 2824 2840 2856 2856 2857 2860 2866 2870 2870 2873 2874 2874 2876 2895 2911 2914 2923 2927 2928 2928 2928 2954 2957 2963 2979 2983 2984 2985 2985 2987 2987 2988 2998 3002 3003 3008 3036 3042 3042 3042 3058 3066 3085 3085 3086 3091 3091 3097 3101 3111 3113 3115 3116 3131 3139 3148 3148 3156 3165 3170 3171 3182 3194 3203 3214 3225 3226 3228 3229 3232 3241 3244 3249 3253 3254 3284 3294 3302 3311 3311 3311 3329 3331 3331 3340 3342 3354 3355 3356 3357 3360 3377 3381 3407 3412 3414 3416 3452 3457 3458 3459 3468 3468 3470 3471 3473 3474 3474 3494 3494 3540 3544 3551 3565 3570 3575 3577 3582 3585 3586 3599 3601 3615 3621 3622 3622 3637 3678 3679 3679 3698 3699 3703 3713 3713 3714 3716 3728 3749 3784 3785 3800 3806 3810 3813 3815 3827 3827 3842 3842 3842 3844 3862 3903 3912 3924 3941 3942 3943 3943 3947 3958 3973 3990 4005 4025 4040 4050 4055 4055 4070 4071 4075 4087 4105 4106 4152 4153 4168 4178 4184 4187 4188 4218 4233 4234 4268 4281 4291 4315 4315 4315 4316 4331 4347 4396 4419 4428 4431 4443 4444 4444 4478 4510 4534 4556 4556 4559 4606 4607 4607 4662 4671 4673 4684 4719 4770 4770 4776 4799 4799 4847 4873 4913 4927 4933 4962 5010 5036 5041 5090 5138 5173 5204 5301 5301 5464";
		assertEquals(expected, result);
	}
}