package coursera.bio.iii;

import static coursera.bio.iii.MiddleEdgeInLinearSpace.middleEdge;

import java.util.Arrays;

public class LinearSpaceAlignment {

//	def linear_space_backtracking(v, w, ioffset = 0, joffset = 0, indel=5, scoring=blosum62):
//	    n = len(v)
//	    m = len(w)
//	    if n==0 and m==0:
//	        # no string to compare, thus no edge
//	        return []
//	    elif n==0:
//	        # return horizontal edges for a w gap
//	#        return [((ioffset,joffset+j),(ioffset,joffset+j+1)) for j in range(m)]
//	        return ['-']*m
//	    elif m==0:
//	        # return vertical edges for a v gap
//	#        return [((ioffset+i,joffset),(ioffset+i+1,joffset)) for i in range(n)]
//	        return ['|']*n
//	        
//	    ((i,j),(k,l)) = middle_edge(v,w,indel=indel,scoring=scoring)
//
//	    if (i==k):
//	        edge = '-'
//	    else:
//	        edge = '/'
//
//	    wleft = w[:j]
//	    wright = w[l:]
//	    vtop = v[:i]
//	    vbottom = v[k:] 
//	    
//	    # back tracking the graph bottom-right part
//	    bottom_right_track = linear_space_backtracking(vbottom, wright, ioffset = k+ioffset,joffset = l+joffset, indel=indel, scoring=scoring)
//	    # back tracking the graph upper-left part
//	    upper_left_track = linear_space_backtracking(vtop, wleft, ioffset = ioffset,joffset = joffset, indel=indel, scoring=scoring)
//	    
//	    return upper_left_track + [edge] + bottom_right_track
	
	private static int[] linearSpaceBacktracking(String v, String w, int ioffset, int joffset) {
		int n = v.length();
		int m = w.length();
		if (n == 0 && m == 0) {
			// no string to compare, thus no edge
			return new int[0];
		} else if (n == 0) {
			// return horizontal edges for a w gap
			int[] a = new int[m];
			Arrays.fill(a, 1); // '-'
			return a;
		} else if (m == 0) {
			// return vertical edges for a v gap
			int[] a = new int[n];
			Arrays.fill(a, 2); // '|'
			return a;
		}
		Tuple<Tuple<Integer, Integer>, Tuple<Integer, Integer>> me = middleEdge.apply(v, w);
		int i = me.getA().a;
		int j = me.getA().b;
		int k = me.getB().a;
		int l = me.getB().b;
		int edge = i == k ? 1 : 3; // '-' -> 1; '/' -> 3
		String wleft = w.substring(0, j);
		String wright = w.substring(l);
		String vtop = v.substring(0, i);
		String vbottom = v.substring(k);
		
		// back tracking the graph bottom-right part
		ioffset = k + ioffset;
		joffset = l + joffset;
		int[] bottom_right_track = linearSpaceBacktracking(vbottom, wright, ioffset, joffset);
		// back tracking the graph upper-left part
		int[] upper_left_track = linearSpaceBacktracking(vtop, wleft, ioffset, joffset);
		int[] result = new int[bottom_right_track.length + upper_left_track.length + 1];
		System.arraycopy(upper_left_track, 0, result, 0, upper_left_track.length);
		System.arraycopy(bottom_right_track, 0, result, upper_left_track.length + 1, bottom_right_track.length);
		result[upper_left_track.length] = edge;
		return result;
	}

//	def backtrack_translation(v,w,bt, indel = 5, scoring = blosum62):
//	    i = 0
//	    j = 0
//	    walign = []
//	    valign = []
//	    smax = 0
//	    for e in bt:
//	        if e == '|':
//	            walign.append('-')
//	            valign.append(v[i])
//	            smax -= indel
//	            i += 1            
//	        elif e == '-':
//	            walign.append(w[j])
//	            valign.append('-')
//	            smax -= indel
//	            j += 1
//	        else:
//	            valign.append(v[i])
//	            walign.append(w[j])
//	            smax += scoring[ v[i] ][ w[j] ]
//	            i += 1          
//	            j += 1 
//
//	#        wleft = w[:j]
//	#        wright = w[j:]
//	#        vtop = v[:i]
//	#        vbottom = v[i:]
//	#        left_len = last_column_score(vtop,wleft)[-1]
//	#        right_len = last_column_score(vbottom,wright)[-1]
//	#        print 'score(',vtop,',',wleft,')=',left_len
//	#        print 'score(',vbottom,',',wright,')=',right_len
//	#        print 'max(',i,',',j,')=',left_len+right_len
//	    return (smax,''.join(valign),''.join(walign))
//	    		
	private static Triplet<Integer, String, String> backtrackTranslation(String v, String w, int[] bt) {
		int indel = 5;
		int i = 0, j = 0, smax = 0;
		StringBuilder valign = new StringBuilder();
		StringBuilder walign = new StringBuilder();
		for (int k = 0; k < bt.length; ++k) {
			if (bt[k] == 2) {
				walign.append('-');
				valign.append(v.charAt(i));
				smax -= indel;
				++i;
			} else if (bt[k] == 1) {
				walign.append(w.charAt(j));
				valign.append('-');
				smax -= indel;
				++j;
			} else {
				valign.append(v.charAt(i));
				walign.append(w.charAt(j));
				smax += Scoring.getBlosum(String.valueOf(v.charAt(i)), String.valueOf(w.charAt(j)));
				++i;
				++j;
			}
//			System.out.println(bt.length + "-- k: " + k + " -- v: " + bt[k]
//					+ " -- i: " + i + " -- j: " + j);
		}
		return new Triplet<Integer, String, String>(smax, valign.toString(), walign.toString());
	}
	
	public static Triplet<Integer, String, String> align(String v, String w) {
		int[] bt = linearSpaceBacktracking(v, w, 0, 0);
		return backtrackTranslation(v, w, bt);
	}

	public static void main(String[] args) {
		String s = "abcde";
		System.out.println(s.substring(0, 2));
		System.out.println(s.substring(2));
	}
}
