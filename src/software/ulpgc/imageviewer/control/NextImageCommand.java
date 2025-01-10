package software.ulpgc.imageviewer.control;

import software.ulpgc.imageviewer.presenter.ImagePresenter;

public class NextImageCommand implements Command {
    private final ImagePresenter presenter;

    public NextImageCommand(ImagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.show(presenter.getImage().next());
    }
}
