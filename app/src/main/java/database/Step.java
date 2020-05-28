package database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Step {

    @PrimaryKey()
    private long stepID;
    private String stepString;
    private int stepNumber;
    private long recipeStepID;

    public Step(long stepID, String stepString, int stepNumber, long recipeStepID) {
        this.stepID = stepID;
        this.stepString = stepString;
        this.stepNumber = stepNumber;
        this.recipeStepID = recipeStepID;
    }

    public void setStepID(long stepID) {
        this.stepID = stepID;
    }

    public String getStepString() {
        return stepString;
    }

    public void setStepString(String stepString) {
        this.stepString = stepString;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public void setRecipeStepID(long recipeStepID) {
        this.recipeStepID = recipeStepID;
    }

    public long getStepID() {
        return stepID;
    }

    public void setStep(String stepString) {
        this.stepString = stepString;
    }

    public String getStep() { return stepString; }

    public long getRecipeStepID() { return recipeStepID; }

    public int getStepNumber() {
        return stepNumber;
    }
}
