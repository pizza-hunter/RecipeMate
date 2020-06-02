package database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Step {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "step_id")
    private long stepID;

    @ColumnInfo(name = "step_string")
    private String stepString;

    @ColumnInfo(name = "step_number")
    private int stepNumber;

    @ColumnInfo(name = "recipe_step_id")
    private long recipeStepID;

    public Step(String stepString, int stepNumber) {
        this.stepString = stepString;
        this.stepNumber = stepNumber;
    }

    public void setStepID(long stepID) {
        this.stepID = stepID;
    }

    public long getStepID() {
        return stepID;
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

    public int getStepNumber() {
        return stepNumber;
    }

    public void setRecipeStepID(long recipeStepID) {
        this.recipeStepID = recipeStepID;
    }

    public long getRecipeStepID() { return recipeStepID; }

}
