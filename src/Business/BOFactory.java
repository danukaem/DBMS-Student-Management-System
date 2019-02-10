package Business;

import Business.Custom.Impl.*;

public class BOFactory {

    public static BOFactory boFactory;

    public enum BOType {
        Course_BO, Batch_BO, Parent_BO, Qlification_BO, Student_BO,StudentReg_BO;
    }

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public <T extends SuperBO> T getBO(BOFactory.BOType boType) {
        switch (boType) {
            case Course_BO:
                return (T) new ManageCourseBOImpl();
            case Batch_BO:
                return (T) new ManageBatchBOImpl();
            case Parent_BO:
                return (T) new ManageParentBOImpl();
            case Qlification_BO:
                return (T) new ManageParentBOImpl();
            case Student_BO:
                return (T) new ManageStudentBOImpl();
            case StudentReg_BO:
                return (T) new ManageRegisterStudentBOImpl();
            default:
                return null;
        }
    }


}
