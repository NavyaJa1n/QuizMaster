/*
 *User class to store user details
 *Navya Jain 23011101084 contributed to designing class User
 *This class acts as base class for user to store their score.
*/
package projectquiz;
class User {
    private String username;
    private int score;

    public User(String username) {
        this.username = username;
        this.score = 0;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }
}
