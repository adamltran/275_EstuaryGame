package game;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class HighScores implements Serializable{
	
	private ArrayList<Score> scores;
	
	public HighScores(){
		scores = new ArrayList<Score>();
	}
	
	public ArrayList<Score> getScores() {
		return scores;
	}

	public boolean isHighScore(int i){
		Collections.sort(scores);
		return (i>scores.get(scores.size()-1).score);
	}
	
	public void add(Score s){
		scores.add(s);
		Collections.sort(scores);
		if(scores.size()>10){
			scores.remove(scores.size()-1);
		}
	}
	
	public void add(int i, String s){
		add(new Score(i,s));
	}

	public class Score implements Serializable,Comparable<Score>{
		private int score;
		private String initials;
		
		public Score(int score, String initials){
			super();
			this.score = score;
			this.initials = initials;
		}

		@Override
		public int compareTo(Score s) {
			return s.score-score;
		}

		public int getScore() {
			return score;
		}

		public String getInitials() {
			return initials;
		}
		
		
	}
}
