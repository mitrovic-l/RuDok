package view;

import controller.actions.ActionManager;
import model.commands.CommandManager;
import view.rightPanels.PresentationView;
import view.tree.controller.RuTreeController;
import model.myNode.myNodeModels.RuWorkspace;
import observer.Message;
import observer.MySubscriber;
import view.bars.MyMenuBar;
import view.bars.MyToolBar;
import view.tree.model.MyTreeModel;
import view.tree.model.MyTreeNode;
import view.tree.view.MyTree;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements MySubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;
    private JToolBar toolBar;
    private JMenuBar menuBar;
    private RuTreeController treeController;
    private JSplitPane splitPane;
    private MyTreeModel model;
    private MyTree myTree;
    private JPanel glavni;
    private JPanel pomKontekst;
    private CommandManager commandManager;


    private MainFrame() {

    }

    private void initialise() {

        actionManager = new ActionManager();
        commandManager = new CommandManager();
        initialiseTree();
        initialiseGUI();
    }

    private void initialiseTree() {

        RuWorkspace ws = new RuWorkspace("Workspace", null);
        MyTreeNode novi = new MyTreeNode(ws);
        model = new MyTreeModel(novi);
        myTree = new MyTree();
        myTree.setModel(model);
        myTree.setEditable(true);

        model.reload();
        myTree.setToggleClickCount(4);


    }

    private void initialiseGUI() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int sirina = screenSize.width;
        int visina = screenSize.height;
        setSize(1400, 900);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("RuDok");
        toolBar = new MyToolBar();
        menuBar = new MyMenuBar();
        setJMenuBar(menuBar);
        add(toolBar, BorderLayout.NORTH);
        JPanel pGlavni = new JPanel();
        JScrollPane pLevi = new JScrollPane(myTree);
        pLevi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel pDesni = new JPanel();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pLevi, pDesni);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(250);
        Dimension minSize = new Dimension(200, 50);
        pLevi.setMinimumSize(minSize);
        pDesni.setMinimumSize(minSize);
        glavni = new JPanel(new GridLayout());
        glavni.add(splitPane);
        pomKontekst = new JPanel(new GridLayout());
        pomKontekst = glavni;
        add(glavni);


        this.revalidate();
        this.repaint();


    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }

    public JPanel getPomKontekst() {
        return pomKontekst;
    }

    public void setPomKontekst(JPanel pomKontekst) {
        this.pomKontekst = pomKontekst;
    }

    public MyTree getTree() {
        return myTree;
    }

    public RuTreeController getTreeController() {
        return treeController;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }


    public void reloadTree() {
        model.reload();
        for (int i = 0; i < myTree.getRowCount(); i++) {
            myTree.expandRow(i);
        }
    }


    public JSplitPane getSplitPane() {
        return splitPane;
    }

    @Override
    public void update(Object notification) {
        Message message = (Message) notification;
         JOptionPane.showMessageDialog(new JDialog(this),message.getText() + "\nResenje: " + message.getSolution(), message.getTitle(), message.getType());

    }

    public JPanel getGlavni() {
        return glavni;
    }

    public void setGlavni(JPanel glavni) {
        this.glavni = glavni;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(JToolBar toolBar) {
        this.toolBar = toolBar;
    }

    public JMenuBar getMyMenuBar() {
        return menuBar;
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }
    public void returnToEditState(){
        this.setJMenuBar(this.getMyMenuBar());
        this.add(this.getToolBar(), BorderLayout.NORTH);
        glavni.removeAll();
        glavni.add(splitPane);
        glavni.revalidate();
        glavni.repaint();
        this.add(glavni);
        this.revalidate();
        this.repaint();
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}
