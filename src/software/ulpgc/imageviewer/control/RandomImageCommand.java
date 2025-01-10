package software.ulpgc.imageviewer.control;

import software.ulpgc.imageviewer.presenter.ImagePresenter;

public class RandomImageCommand implements Command{
    private final ImagePresenter presenter;

    public RandomImageCommand(ImagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.show(presenter.getImage().randomnext());
    }
}
