package robotgrid.entity.active.controller;

public interface ICommand {

    public abstract CommandResult execute(final Controller controller);

}
