package delegation.office;

import delegation.office.Clerk;
import delegation.office.Employee;
import delegation.office.Manager;
import delegation.office.Printer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import junit.framework.TestCase;
import no.hal.jex.runtime.JExercise;
import org.eclipse.xtext.xbase.lib.DoubleExtensions;

@JExercise(description = "Tests delegation.office.Manager")
@SuppressWarnings("all")
public class ManagerTest extends TestCase {
  private Printer printer;
  
  private Printer _init_printer() {
    Printer _printer = new Printer();
    return _printer;
  }
  
  private Clerk clerk;
  
  private Clerk _init_clerk() {
    Clerk _clerk = new Clerk(this.printer);
    return _clerk;
  }
  
  private Clerk secondClerk;
  
  private Clerk _init_secondClerk() {
    Clerk _clerk = new Clerk(this.printer);
    return _clerk;
  }
  
  private Manager manager;
  
  private Manager _init_manager() {
    List<Employee> _asList = Arrays.<Employee>asList(this.clerk);
    Manager _manager = new Manager(_asList);
    return _manager;
  }
  
  @Override
  protected void setUp() {
    printer = _init_printer();
    clerk = _init_clerk();
    secondClerk = _init_secondClerk();
    manager = _init_manager();
    
  }
  
  private boolean operator_assertEquals(final double first, final double second) {
    TestCase.assertEquals(first, second, 0.000001d);
    return true;
  }
  
  @JExercise(tests = "Manager(java.util.Collection<delegation.office.Employee>)", description = "Tests \n\t\tthe following sequence:\n\t\t<ul>\n\t\t<li>new Manager(new ArrayList<Employee>)</li>\n\t\t</ul>\n")
  public void testNoEmployeesConstructor() {
    try {
      _transition_exprAction__noEmployeesConstructor_transitions0_actions0();
      fail("IllegalArgumentException should be thrown after new Manager(new ArrayList<Employee>)");
    } catch (Exception e) {
      assertTrue("IllegalArgumentException should be thrown after new Manager(new ArrayList<Employee>)", e instanceof IllegalArgumentException);
    }
    
  }
  
  @JExercise(tests = "Manager(java.util.Collection<delegation.office.Employee>)", description = "Tests \n\t\tinitialization\n")
  public void testDoCalculations() {
    _test__doCalculations_transitions0_effect_state();
    
  }
  
  @JExercise(tests = "Manager(java.util.Collection<delegation.office.Employee>);void printDocument(String)", description = "Tests \n\t\tthe following sequence:\n\t\t<ul>\n\t\t<li>Printer et dokument: manager.printDocument(\"dokument\")</li>\n\t\t</ul>\n")
  public void testPrintDocuments() {
    _transition_exprAction__printDocuments_transitions0_actions0();
    _test__printDocuments_transitions0_effect_state();
    
  }
  
  @JExercise(tests = "Manager(java.util.Collection<delegation.office.Employee>);void printDocument(String);double doCalculations(java.util.function.BinaryOperator<Double>,double,double)", description = "Tests \n\t\tthe following sequence:\n\t\t<ul>\n\t\t<li>Printer et dokument: manager.printDocument(\"dokument\")</li>\n\t\t<li>Gj\u00F8r en beregning: manager.doCalculations([x, y | x + y ], 2.0, 3.5) ?= 5.5</li>\n\t\t</ul>\n")
  public void testTaskCount() {
    _test__taskCount_transitions0_effect_state();
    _transition_exprAction__taskCount_transitions1_actions0();
    _test__taskCount_transitions1_effect_state();
    _transition_exprAction__taskCount_transitions2_actions0();
    _test__taskCount_transitions2_effect_state();
    
  }
  
  @JExercise(tests = "Manager(java.util.Collection<delegation.office.Employee>)", description = "Tests \n\t\tinitialization\n")
  public void testResourceCount() {
    _test__resourceCount_transitions0_effect_state();
    
  }
  
  @JExercise(tests = "Manager(java.util.Collection<delegation.office.Employee>);void printDocument(String);double doCalculations(java.util.function.BinaryOperator<Double>,double,double)", description = "Tests \n\t\tthe following sequence:\n\t\t<ul>\n\t\t<li>Printer et dokument: multiManager.printDocument(\"dokument\")</li>\n\t\t<li>Gj\u00F8r en beregning: multiManager.doCalculations([x, y | x + y ], 2.0, 3.5) ?= 5.5</li>\n\t\t</ul>\n")
  public void testSeveralClerks() {
    Manager multiManager = _init__severalClerks_multiManager();
    _test__severalClerks_transitions0_effect_state(multiManager);
    _transition_exprAction__severalClerks_transitions1_actions0(multiManager);
    _test__severalClerks_transitions1_effect_state(multiManager);
    _transition_exprAction__severalClerks_transitions2_actions0(multiManager);
    _test__severalClerks_transitions2_effect_state(multiManager);
    
  }
  
  private void _transition_exprAction__noEmployeesConstructor_transitions0_actions0() {
    try {
      
      ArrayList<Employee> _arrayList = new ArrayList<Employee>();
      new Manager(_arrayList);
      } catch (junit.framework.AssertionFailedError error) {
      fail("new Manager(new ArrayList<Employee>) failed: " + error.getMessage());
    }
    
  }
  
  private void _test__doCalculations_transitions0_effect_state() {
    _test__doCalculations_transitions0_effect_state_objectTests0_test();
    
  }
  
  private void _test__doCalculations_transitions0_effect_state_objectTests0_test() {
    
    final BinaryOperator<Double> _function = new BinaryOperator<Double>() {
      public Double apply(final Double x, final Double y) {
        return Double.valueOf(DoubleExtensions.operator_plus(x, y));
      }
    };
    double _doCalculations = this.manager.doCalculations(_function, 2.0, 3.5);
    boolean _assertEquals = this.operator_assertEquals(_doCalculations, 5.5);
    assertTrue("manager.doCalculations([x, y | x + y ], 2.0, 3.5) ?= 5.5 failed", _assertEquals);
    
    int _taskCount = this.clerk.taskCount();
    assertEquals("clerk.taskCount == 1 failed", 1, _taskCount);
    
    int _taskCount_1 = this.manager.taskCount();
    assertEquals("manager.taskCount == 1 failed", 1, _taskCount_1);
    
  }
  
  private void _transition_exprAction__printDocuments_transitions0_actions0() {
    try {
      
      this.manager.printDocument("dokument");
      } catch (junit.framework.AssertionFailedError error) {
      fail("manager.printDocument(\"dokument\") failed: " + error.getMessage());
    }
    
  }
  
  private void _test__printDocuments_transitions0_effect_state() {
    _test__printDocuments_transitions0_effect_state_objectTests0_test();
    
  }
  
  private void _test__printDocuments_transitions0_effect_state_objectTests0_test() {
    
    List<String> _printHistory = this.printer.getPrintHistory();
    String _get = _printHistory.get(0);
    assertEquals("printer.printHistory.get(0) == \"dokument1\" failed after manager.printDocument(\"dokument\")", "dokument1", _get);
    
    int _taskCount = this.clerk.taskCount();
    assertEquals("clerk.taskCount == 1 failed after manager.printDocument(\"dokument\")", 1, _taskCount);
    
    List<String> _printHistory_1 = this.printer.getPrintHistory();
    int _size = _printHistory_1.size();
    assertEquals("printer.printHistory.size == 1 failed after manager.printDocument(\"dokument\")", 1, _size);
    
  }
  
  private void _test__taskCount_transitions0_effect_state() {
    _test__taskCount_transitions0_effect_state_objectTests0_test();
    
  }
  
  private void _test__taskCount_transitions0_effect_state_objectTests0_test() {
    
    int _taskCount = this.manager.taskCount();
    assertEquals("manager.taskCount == 0 failed", 0, _taskCount);
    
    int _taskCount_1 = this.clerk.taskCount();
    assertEquals("clerk.taskCount == 0 failed", 0, _taskCount_1);
    
  }
  
  private void _transition_exprAction__taskCount_transitions1_actions0() {
    try {
      
      this.manager.printDocument("dokument");
      } catch (junit.framework.AssertionFailedError error) {
      fail("manager.printDocument(\"dokument\") failed: " + error.getMessage());
    }
    
  }
  
  private void _test__taskCount_transitions1_effect_state() {
    _test__taskCount_transitions1_effect_state_objectTests0_test();
    
  }
  
  private void _test__taskCount_transitions1_effect_state_objectTests0_test() {
    
    int _taskCount = this.manager.taskCount();
    assertEquals("manager.taskCount == 1 failed after manager.printDocument(\"dokument\")", 1, _taskCount);
    
    int _taskCount_1 = this.clerk.taskCount();
    assertEquals("clerk.taskCount == 1 failed after manager.printDocument(\"dokument\")", 1, _taskCount_1);
    
  }
  
  private void _transition_exprAction__taskCount_transitions2_actions0() {
    
    final BinaryOperator<Double> _function = new BinaryOperator<Double>() {
      public Double apply(final Double x, final Double y) {
        return Double.valueOf(DoubleExtensions.operator_plus(x, y));
      }
    };
    double _doCalculations = this.manager.doCalculations(_function, 2.0, 3.5);
    boolean _assertEquals = this.operator_assertEquals(_doCalculations, 5.5);
    assertTrue("manager.doCalculations([x, y | x + y ], 2.0, 3.5) ?= 5.5 failed", Boolean.valueOf(_assertEquals));
    
  }
  
  private void _test__taskCount_transitions2_effect_state() {
    _test__taskCount_transitions2_effect_state_objectTests0_test();
    
  }
  
  private void _test__taskCount_transitions2_effect_state_objectTests0_test() {
    
    int _taskCount = this.manager.taskCount();
    assertEquals("manager.taskCount == 2 failed after manager.doCalculations([x, y | x + y ], 2.0, 3.5) ?= 5.5", 2, _taskCount);
    
    int _taskCount_1 = this.clerk.taskCount();
    assertEquals("clerk.taskCount == 2 failed after manager.doCalculations([x, y | x + y ], 2.0, 3.5) ?= 5.5", 2, _taskCount_1);
    
  }
  
  private void _test__resourceCount_transitions0_effect_state() {
    _test__resourceCount_transitions0_effect_state_objectTests0_test();
    
  }
  
  private void _test__resourceCount_transitions0_effect_state_objectTests0_test() {
    
    int _resourceCount = this.manager.resourceCount();
    assertEquals("manager.resourceCount == 2 failed", 2, _resourceCount);
    
  }
  
  private Manager _init__severalClerks_multiManager() {
    List<Employee> _asList = Arrays.<Employee>asList(this.clerk, this.secondClerk);
    Manager _manager = new Manager(_asList);
    return _manager;
  }
  
  private void _test__severalClerks_transitions0_effect_state(final Manager multiManager) {
    _test__severalClerks_transitions0_effect_state_objectTests0_test(multiManager);
    
  }
  
  private void _test__severalClerks_transitions0_effect_state_objectTests0_test(final Manager multiManager) {
    
    int _resourceCount = multiManager.resourceCount();
    assertEquals("multiManager.resourceCount == 3 failed", 3, _resourceCount);
    
    int _resourceCount_1 = this.clerk.resourceCount();
    assertEquals("clerk.resourceCount == 1 failed", 1, _resourceCount_1);
    
    int _resourceCount_2 = this.secondClerk.resourceCount();
    assertEquals("secondClerk.resourceCount == 1 failed", 1, _resourceCount_2);
    
  }
  
  private void _transition_exprAction__severalClerks_transitions1_actions0(final Manager multiManager) {
    try {
      
      multiManager.printDocument("dokument");
      } catch (junit.framework.AssertionFailedError error) {
      fail("multiManager.printDocument(\"dokument\") failed: " + error.getMessage());
    }
    
  }
  
  private void _test__severalClerks_transitions1_effect_state(final Manager multiManager) {
    _test__severalClerks_transitions1_effect_state_objectTests0_test(multiManager);
    
  }
  
  private void _test__severalClerks_transitions1_effect_state_objectTests0_test(final Manager multiManager) {
    
    int _taskCount = multiManager.taskCount();
    assertEquals("multiManager.taskCount == 1 failed after multiManager.printDocument(\"dokument\")", 1, _taskCount);
    
    boolean _and = false;
    boolean _or = false;
    int _taskCount_1 = this.clerk.taskCount();
    boolean _equals = (_taskCount_1 == 1);
    if (_equals) {
      _or = true;
    } else {
      int _taskCount_2 = this.secondClerk.taskCount();
      boolean _equals_1 = (_taskCount_2 == 1);
      _or = _equals_1;
    }
    if (!_or) {
      _and = false;
    } else {
      boolean _or_1 = false;
      int _taskCount_3 = this.clerk.taskCount();
      boolean _equals_2 = (_taskCount_3 == 0);
      if (_equals_2) {
        _or_1 = true;
      } else {
        int _taskCount_4 = this.secondClerk.taskCount();
        boolean _equals_3 = (_taskCount_4 == 0);
        _or_1 = _equals_3;
      }
      _and = _or_1;
    }
    assertTrue("(clerk.taskCount == 1 || secondClerk.taskCount == 1) && (clerk.taskCount == 0 || secondClerk.taskCount == 0) failed after multiManager.printDocument(\"dokument\")", _and);
    
  }
  
  private void _transition_exprAction__severalClerks_transitions2_actions0(final Manager multiManager) {
    
    final BinaryOperator<Double> _function = new BinaryOperator<Double>() {
      public Double apply(final Double x, final Double y) {
        return Double.valueOf(DoubleExtensions.operator_plus(x, y));
      }
    };
    double _doCalculations = multiManager.doCalculations(_function, 2.0, 3.5);
    boolean _assertEquals = this.operator_assertEquals(_doCalculations, 5.5);
    assertTrue("multiManager.doCalculations([x, y | x + y ], 2.0, 3.5) ?= 5.5 failed", Boolean.valueOf(_assertEquals));
    
  }
  
  private void _test__severalClerks_transitions2_effect_state(final Manager multiManager) {
    _test__severalClerks_transitions2_effect_state_objectTests0_test(multiManager);
    
  }
  
  private void _test__severalClerks_transitions2_effect_state_objectTests0_test(final Manager multiManager) {
    
    int _taskCount = this.manager.taskCount();
    assertEquals("manager.taskCount == 2 failed after multiManager.doCalculations([x, y | x + y ], 2.0, 3.5) ?= 5.5", 2, _taskCount);
    
  }
  
  public static void main(final String[] args) {
    no.hal.jex.standalone.JexStandalone.main(ManagerTest.class);
  }
}
