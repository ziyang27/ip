package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;
import dibo.task.Deadline;

import java.time.LocalDate;

/**
 * Class to handle the command which adds a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDate by;

    /**
     * Constructor for the AddDeadlineCommand class.
     *
     * @param description The description of deadline task.
     * @param by The LocalDate object of the deadline of the task.
     */
    public AddDeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Run the add deadline task command.
     *
     * @param taskList The TaskList object which contains all the tasks.
     * @param ui The Ui object which is responsible for printing the added message.
     * @param storage The Storage object which is responsible to save the changes into a text file.
     * @throws DiboException when an error occurs when trying to save the changes into a text file.
     */
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        Deadline deadline = new Deadline(this.description, this.by);
        taskList.addTask(deadline);
        ui.showAdded(deadline.toString(), taskList.getSize());
        storage.save(taskList);
    }
}