package robotgrid.entity.active.controller;

import robotgrid.utils.Result;

public interface ICommand {

    public abstract Result<Void, String> execute(final Controller controller, final String[] parts);

}
