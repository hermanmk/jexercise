package no.hal.jex.jextest.jvmmodel;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import no.hal.jex.jextest.jexTest.Instance;
import no.hal.jex.jextest.jexTest.JexTestCase;
import no.hal.jex.jextest.jexTest.JexTestSequence;
import no.hal.jex.jextest.jexTest.Method;
import no.hal.jex.jextest.jexTest.ObjectTest;
import no.hal.jex.jextest.jexTest.Parameter;
import no.hal.jex.jextest.jexTest.PropertiesTest;
import no.hal.jex.jextest.jexTest.State;
import no.hal.jex.jextest.jexTest.StateFunction;
import no.hal.jex.jextest.jexTest.StateTestContext;
import no.hal.jex.jextest.jexTest.Transition;
import no.hal.jex.jextest.jexTest.TransitionAction;
import no.hal.jex.jextest.jexTest.TransitionEffect;
import no.hal.jex.jextest.jexTest.TransitionExceptionEffect;
import no.hal.jex.jextest.jexTest.TransitionExpressionAction;
import no.hal.jex.jextest.jexTest.TransitionSource;
import no.hal.jex.jextest.jexTest.TransitionTargetEffect;
import no.hal.jex.jextest.jvmmodel.TestAnnotationsSupport;
import no.hal.jex.jextest.jvmmodel.Util;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.common.types.JvmAnnotationReference;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.XBinaryOperation;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XUnaryOperation;
import org.eclipse.xtext.xbase.compiler.TypeReferenceSerializer;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor.IPostIndexingInitializing;
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociator;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.eclipse.xtext.xbase.scoping.XbaseScopeProvider;
import org.eclipse.xtext.xbase.scoping.featurecalls.OperatorMapping;

/**
 * <p>Infers a JVM model from the source model.</p>
 * 
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
@SuppressWarnings("all")
public class JexTestJvmModelInferrer extends AbstractModelInferrer {
  @Inject
  @Extension
  private JvmTypesBuilder _jvmTypesBuilder;
  
  @Inject
  @Extension
  private TypeReferenceSerializer _typeReferenceSerializer;
  
  @Inject
  @Extension
  private XbaseCompiler _xbaseCompiler;
  
  @Inject
  @Extension
  private IJvmModelAssociator _iJvmModelAssociator;
  
  @Inject
  @Extension
  private OperatorMapping _operatorMapping;
  
  @Inject
  @Extension
  private Util _util;
  
  @Inject
  @Extension
  private TestAnnotationsSupport _testAnnotationsSupport;
  
  protected void _infer(final JexTestCase testCase, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    String _elvis = null;
    String _testClassName = testCase.getTestClassName();
    if (_testClassName != null) {
      _elvis = _testClassName;
    } else {
      JvmParameterizedTypeReference _testedClass = testCase.getTestedClass();
      String _qualifiedName = _testedClass.getQualifiedName();
      String _plus = (_qualifiedName + "Test");
      _elvis = ObjectExtensions.<String>operator_elvis(_testClassName, _plus);
    }
    final String className = _elvis;
    final JvmGenericType jvmClass = this._jvmTypesBuilder.toClass(testCase, className);
    EList<JvmTypeReference> _superTypes = jvmClass.getSuperTypes();
    JvmTypeReference _newTypeRef = this._jvmTypesBuilder.newTypeRef(testCase, "junit.framework.TestCase");
    this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes, _newTypeRef);
    IPostIndexingInitializing<JvmGenericType> _accept = acceptor.<JvmGenericType>accept(jvmClass);
    final Procedure1<JvmGenericType> _function = new Procedure1<JvmGenericType>() {
      public void apply(final JvmGenericType it) {
        final JvmAnnotationReference jexerciseTestCaseAnnotation = JexTestJvmModelInferrer.this._jvmTypesBuilder.toAnnotation(testCase, "no.hal.jex.runtime.JExercise");
        JexTestJvmModelInferrer.this._testAnnotationsSupport.generateTestClassAnntations(testCase, jexerciseTestCaseAnnotation);
        EList<JvmAnnotationReference> _annotations = it.getAnnotations();
        JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations, jexerciseTestCaseAnnotation);
        boolean _isDefaultInstanceTest = JexTestJvmModelInferrer.this._util.isDefaultInstanceTest(testCase);
        if (_isDefaultInstanceTest) {
          EList<JvmMember> _members = jvmClass.getMembers();
          String _defaultInstanceName = JexTestJvmModelInferrer.this._util.defaultInstanceName(testCase);
          JvmParameterizedTypeReference _testedClass = testCase.getTestedClass();
          final Procedure1<JvmField> _function = new Procedure1<JvmField>() {
            public void apply(final JvmField it) {
              it.setVisibility(JvmVisibility.PRIVATE);
            }
          };
          JvmField _field = JexTestJvmModelInferrer.this._jvmTypesBuilder.toField(testCase, _defaultInstanceName, _testedClass, _function);
          JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmField>operator_add(_members, _field);
        } else {
          EList<Instance> _instances = testCase.getInstances();
          for (final Instance instance : _instances) {
            {
              final JvmParameterizedTypeReference type = JexTestJvmModelInferrer.this._util.jvmType(instance);
              EList<JvmMember> _members_1 = jvmClass.getMembers();
              String _name = instance.getName();
              final Procedure1<JvmField> _function_1 = new Procedure1<JvmField>() {
                public void apply(final JvmField it) {
                  it.setVisibility(JvmVisibility.PRIVATE);
                }
              };
              JvmField _field_1 = JexTestJvmModelInferrer.this._jvmTypesBuilder.toField(instance, _name, type, _function_1);
              JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmField>operator_add(_members_1, _field_1);
              EList<JvmMember> _members_2 = it.getMembers();
              String _name_1 = instance.getName();
              String _plus = ("_init_" + _name_1);
              JvmOperation _inferTestInstanceInitMethod = JexTestJvmModelInferrer.this.inferTestInstanceInitMethod(_plus, instance);
              JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmOperation>operator_add(_members_2, _inferTestInstanceInitMethod);
            }
          }
        }
        EList<JvmMember> _members_1 = jvmClass.getMembers();
        JvmTypeReference _newTypeRef = JexTestJvmModelInferrer.this._jvmTypesBuilder.newTypeRef(testCase, void.class);
        final Procedure1<JvmOperation> _function_1 = new Procedure1<JvmOperation>() {
          public void apply(final JvmOperation it) {
            it.setVisibility(JvmVisibility.PROTECTED);
            EList<JvmAnnotationReference> _annotations = it.getAnnotations();
            JvmAnnotationReference _annotation = JexTestJvmModelInferrer.this._jvmTypesBuilder.toAnnotation(testCase, Override.class);
            JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations, _annotation);
            final Procedure1<ITreeAppendable> _function = new Procedure1<ITreeAppendable>() {
              public void apply(final ITreeAppendable it) {
                JexTestJvmModelInferrer.this.generateSetUpMethodBody(testCase, it);
              }
            };
            JexTestJvmModelInferrer.this._jvmTypesBuilder.setBody(it, _function);
          }
        };
        JvmOperation _method = JexTestJvmModelInferrer.this._jvmTypesBuilder.toMethod(testCase, "setUp", _newTypeRef, _function_1);
        JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmOperation>operator_add(_members_1, _method);
        EList<Method> _methods = testCase.getMethods();
        for (final Method method : _methods) {
          {
            String _elvis = null;
            String _name = method.getName();
            QualifiedName _create = QualifiedName.create(_name);
            QualifiedName _methodName = JexTestJvmModelInferrer.this._operatorMapping.getMethodName(_create);
            String _string = null;
            if (_methodName!=null) {
              _string=_methodName.toString();
            }
            if (_string != null) {
              _elvis = _string;
            } else {
              String _name_1 = method.getName();
              _elvis = ObjectExtensions.<String>operator_elvis(_string, _name_1);
            }
            final String methodName = _elvis;
            boolean _notEquals = (!Objects.equal(methodName, null));
            if (_notEquals) {
              EList<JvmMember> _members_2 = it.getMembers();
              JvmParameterizedTypeReference _returnType = method.getReturnType();
              final Procedure1<JvmOperation> _function_2 = new Procedure1<JvmOperation>() {
                public void apply(final JvmOperation it) {
                  it.setVisibility(JvmVisibility.PRIVATE);
                  boolean _isStatic = method.isStatic();
                  it.setStatic(_isStatic);
                  EList<Parameter> _parameters = method.getParameters();
                  for (final Parameter parameter : _parameters) {
                    EList<JvmFormalParameter> _parameters_1 = it.getParameters();
                    String _name = parameter.getName();
                    JvmParameterizedTypeReference _type = parameter.getType();
                    JvmFormalParameter _parameter = JexTestJvmModelInferrer.this._jvmTypesBuilder.toParameter(parameter, _name, _type);
                    JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter);
                  }
                  XExpression _body = method.getBody();
                  JexTestJvmModelInferrer.this._jvmTypesBuilder.setBody(it, _body);
                }
              };
              JvmOperation _method_1 = JexTestJvmModelInferrer.this._jvmTypesBuilder.toMethod(method, methodName, _returnType, _function_2);
              JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmOperation>operator_add(_members_2, _method_1);
            }
          }
        }
        EList<StateFunction> _stateFunctions = testCase.getStateFunctions();
        for (final StateFunction stateFunction : _stateFunctions) {
          String _name = stateFunction.getName();
          boolean _notEquals = (!Objects.equal(_name, null));
          if (_notEquals) {
            String _name_1 = stateFunction.getName();
            JvmTypeReference _newTypeRef_1 = JexTestJvmModelInferrer.this._jvmTypesBuilder.newTypeRef(stateFunction, void.class);
            final Procedure1<JvmOperation> _function_2 = new Procedure1<JvmOperation>() {
              public void apply(final JvmOperation it) {
                it.setVisibility(JvmVisibility.PRIVATE);
                EList<JvmFormalParameter> _parameters = it.getParameters();
                String _string = XbaseScopeProvider.IT.toString();
                JvmParameterizedTypeReference _elvis = null;
                JvmParameterizedTypeReference _type = stateFunction.getType();
                if (_type != null) {
                  _elvis = _type;
                } else {
                  JvmParameterizedTypeReference _testedClass = testCase.getTestedClass();
                  _elvis = ObjectExtensions.<JvmParameterizedTypeReference>operator_elvis(_type, _testedClass);
                }
                JvmFormalParameter _parameter = JexTestJvmModelInferrer.this._jvmTypesBuilder.toParameter(stateFunction, _string, _elvis);
                JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
                EList<Parameter> _parameters_1 = stateFunction.getParameters();
                for (final Parameter parameter : _parameters_1) {
                  EList<JvmFormalParameter> _parameters_2 = it.getParameters();
                  String _name = parameter.getName();
                  JvmParameterizedTypeReference _type_1 = parameter.getType();
                  JvmFormalParameter _parameter_1 = JexTestJvmModelInferrer.this._jvmTypesBuilder.toParameter(parameter, _name, _type_1);
                  JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_1);
                }
                final Procedure1<ITreeAppendable> _function = new Procedure1<ITreeAppendable>() {
                  public void apply(final ITreeAppendable it) {
                    XBlockExpression _test = stateFunction.getTest();
                    JexTestJvmModelInferrer.this.generateTestHelperMethodCall("_test_", ((PropertiesTest) _test), it);
                  }
                };
                JexTestJvmModelInferrer.this._jvmTypesBuilder.setBody(it, _function);
              }
            };
            final JvmOperation stateMethod = JexTestJvmModelInferrer.this._jvmTypesBuilder.toMethod(stateFunction, _name_1, _newTypeRef_1, _function_2);
            EList<JvmMember> _members_2 = it.getMembers();
            JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmOperation>operator_add(_members_2, stateMethod);
            XBlockExpression _test = stateFunction.getTest();
            boolean _notEquals_1 = (!Objects.equal(_test, null));
            if (_notEquals_1) {
              XBlockExpression _test_1 = stateFunction.getTest();
              JexTestJvmModelInferrer.this.inferPropertiesTestMethods(((PropertiesTest) _test_1), jvmClass);
            }
          }
        }
        final ArrayList<Pair<JexTestSequence,JvmOperation>> testMethods = CollectionLiterals.<Pair<JexTestSequence,JvmOperation>>newArrayList();
        EList<JexTestSequence> _testSequences = testCase.getTestSequences();
        for (final JexTestSequence sequence : _testSequences) {
          String _name_2 = sequence.getName();
          boolean _notEquals_2 = (!Objects.equal(_name_2, null));
          if (_notEquals_2) {
            String _name_3 = sequence.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name_3);
            String _plus = ("test" + _firstUpper);
            JvmTypeReference _newTypeRef_2 = JexTestJvmModelInferrer.this._jvmTypesBuilder.newTypeRef(sequence, void.class);
            final Procedure1<JvmOperation> _function_3 = new Procedure1<JvmOperation>() {
              public void apply(final JvmOperation it) {
                it.setVisibility(JvmVisibility.PUBLIC);
                final Procedure1<ITreeAppendable> _function = new Procedure1<ITreeAppendable>() {
                  public void apply(final ITreeAppendable it) {
                    EList<Instance> _instances = sequence.getInstances();
                    for (final Instance instance : _instances) {
                      JexTestJvmModelInferrer.this.generateLocalInstance(instance, it);
                    }
                    EList<Transition> _transitions = sequence.getTransitions();
                    for (final Transition transition : _transitions) {
                      JexTestJvmModelInferrer.this.generateTransition(transition, it);
                    }
                  }
                };
                JexTestJvmModelInferrer.this._jvmTypesBuilder.setBody(it, _function);
              }
            };
            final JvmOperation testMethod = JexTestJvmModelInferrer.this._jvmTypesBuilder.toMethod(sequence, _plus, _newTypeRef_2, _function_3);
            Pair<JexTestSequence,JvmOperation> _mappedTo = Pair.<JexTestSequence, JvmOperation>of(sequence, testMethod);
            testMethods.add(_mappedTo);
            EList<JvmMember> _members_3 = it.getMembers();
            JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmOperation>operator_add(_members_3, testMethod);
          }
        }
        EList<JvmMember> _members_4 = it.getMembers();
        final Function1<Pair<JexTestSequence,JvmOperation>,JvmOperation> _function_4 = new Function1<Pair<JexTestSequence,JvmOperation>,JvmOperation>() {
          public JvmOperation apply(final Pair<JexTestSequence,JvmOperation> it) {
            JvmOperation _value = it.getValue();
            return _value;
          }
        };
        List<JvmMember> _map = ListExtensions.<Pair<JexTestSequence,JvmOperation>, JvmMember>map(testMethods, _function_4);
        JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmMember>operator_add(_members_4, _map);
        EList<JexTestSequence> _testSequences_1 = testCase.getTestSequences();
        for (final JexTestSequence sequence_1 : _testSequences_1) {
          {
            EList<Instance> _instances_1 = sequence_1.getInstances();
            for (final Instance instance_1 : _instances_1) {
              EList<JvmMember> _members_5 = it.getMembers();
              String _relativeName = JexTestJvmModelInferrer.this._util.<JexTestSequence>relativeName(instance_1, JexTestSequence.class);
              String _plus_1 = ("_init_" + _relativeName);
              JvmOperation _inferTestInstanceInitMethod = JexTestJvmModelInferrer.this.inferTestInstanceInitMethod(_plus_1, instance_1);
              JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmOperation>operator_add(_members_5, _inferTestInstanceInitMethod);
            }
            EList<Transition> _transitions = sequence_1.getTransitions();
            for (final Transition transition : _transitions) {
              {
                boolean _and = false;
                TransitionSource _source = transition.getSource();
                boolean _notEquals_3 = (!Objects.equal(_source, null));
                if (!_notEquals_3) {
                  _and = false;
                } else {
                  TransitionSource _source_1 = transition.getSource();
                  State _state = _source_1.getState();
                  boolean _notEquals_4 = (!Objects.equal(_state, null));
                  _and = (_notEquals_3 && _notEquals_4);
                }
                if (_and) {
                  TransitionSource _source_2 = transition.getSource();
                  State _state_1 = _source_2.getState();
                  JexTestJvmModelInferrer.this.inferStateTestMethods(sequence_1, _state_1, jvmClass);
                }
                EList<TransitionAction> _actions = transition.getActions();
                Iterable<TransitionExpressionAction> _filter = Iterables.<TransitionExpressionAction>filter(_actions, TransitionExpressionAction.class);
                for (final TransitionExpressionAction action : _filter) {
                  {
                    EList<JvmMember> _members_6 = it.getMembers();
                    JvmOperation _inferActionMethod = JexTestJvmModelInferrer.this.inferActionMethod(sequence_1, action);
                    JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmOperation>operator_add(_members_6, _inferActionMethod);
                    XExpression _times = action.getTimes();
                    boolean _notEquals_5 = (!Objects.equal(_times, null));
                    if (_notEquals_5) {
                      EList<JvmMember> _members_7 = it.getMembers();
                      XExpression _times_1 = action.getTimes();
                      JvmOperation _inferTestHelperMethod = JexTestJvmModelInferrer.this.inferTestHelperMethod(sequence_1, "_transition_exprAction_times_", action, _times_1, null);
                      JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmOperation>operator_add(_members_7, _inferTestHelperMethod);
                    }
                  }
                }
                TransitionEffect _effect = transition.getEffect();
                if ((_effect instanceof TransitionTargetEffect)) {
                  TransitionEffect _effect_1 = transition.getEffect();
                  final TransitionTargetEffect targetEffect = ((TransitionTargetEffect) _effect_1);
                  State _state_2 = targetEffect.getState();
                  boolean _notEquals_5 = (!Objects.equal(_state_2, null));
                  if (_notEquals_5) {
                    State _state_3 = targetEffect.getState();
                    JexTestJvmModelInferrer.this.inferStateTestMethods(sequence_1, _state_3, jvmClass);
                  }
                }
              }
            }
          }
        }
        for (final Pair<JexTestSequence,JvmOperation> sequenceMethod : testMethods) {
          {
            JexTestSequence _key = sequenceMethod.getKey();
            final JvmAnnotationReference jexerciseTestMethodAnnotation = JexTestJvmModelInferrer.this._jvmTypesBuilder.toAnnotation(_key, "no.hal.jex.runtime.JExercise");
            JexTestSequence _key_1 = sequenceMethod.getKey();
            JexTestJvmModelInferrer.this._testAnnotationsSupport.generateTestMethodAnntations(_key_1, jexerciseTestMethodAnnotation);
            JvmOperation _value = sequenceMethod.getValue();
            EList<JvmAnnotationReference> _annotations_1 = _value.getAnnotations();
            JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations_1, jexerciseTestMethodAnnotation);
          }
        }
        EList<JvmMember> _members_5 = it.getMembers();
        JvmTypeReference _newTypeRef_3 = JexTestJvmModelInferrer.this._jvmTypesBuilder.newTypeRef(testCase, void.class);
        final Procedure1<JvmOperation> _function_5 = new Procedure1<JvmOperation>() {
          public void apply(final JvmOperation it) {
            it.setVisibility(JvmVisibility.PUBLIC);
            it.setStatic(true);
            EList<JvmFormalParameter> _parameters = it.getParameters();
            JvmTypeReference _newTypeRef = JexTestJvmModelInferrer.this._jvmTypesBuilder.newTypeRef(testCase, String.class);
            JvmTypeReference _addArrayTypeDimension = JexTestJvmModelInferrer.this._jvmTypesBuilder.addArrayTypeDimension(_newTypeRef);
            JvmFormalParameter _parameter = JexTestJvmModelInferrer.this._jvmTypesBuilder.toParameter(testCase, "args", _addArrayTypeDimension);
            JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
            final Procedure1<ITreeAppendable> _function = new Procedure1<ITreeAppendable>() {
              public void apply(final ITreeAppendable it) {
                StringConcatenation _builder = new StringConcatenation();
                _builder.append("no.hal.jex.standalone.JexStandalone.main(");
                String _simpleName = jvmClass.getSimpleName();
                _builder.append(_simpleName, "");
                _builder.append(".class);");
                it.append(_builder);
              }
            };
            JexTestJvmModelInferrer.this._jvmTypesBuilder.setBody(it, _function);
          }
        };
        JvmOperation _method_1 = JexTestJvmModelInferrer.this._jvmTypesBuilder.toMethod(testCase, "main", _newTypeRef_3, _function_5);
        JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmOperation>operator_add(_members_5, _method_1);
      }
    };
    _accept.initializeLater(_function);
  }
  
  private boolean checkDiagnosticInCompiler = true;
  
  public JvmOperation inferTestInstanceInitMethod(final String name, final Instance instance) {
    JvmOperation _xblockexpression = null;
    {
      JvmParameterizedTypeReference _jvmType = this._util.jvmType(instance);
      final Procedure1<JvmOperation> _function = new Procedure1<JvmOperation>() {
        public void apply(final JvmOperation it) {
          it.setVisibility(JvmVisibility.PRIVATE);
        }
      };
      final JvmOperation method = this._jvmTypesBuilder.toMethod(instance, name, _jvmType, _function);
      XExpression _expr = instance.getExpr();
      boolean _equals = Objects.equal(_expr, null);
      if (_equals) {
        final Procedure1<ITreeAppendable> _function_1 = new Procedure1<ITreeAppendable>() {
          public void apply(final ITreeAppendable it) {
            it.append("return new ");
            JvmParameterizedTypeReference _jvmType = JexTestJvmModelInferrer.this._util.jvmType(instance);
            JexTestJvmModelInferrer.this._typeReferenceSerializer.serialize(_jvmType, instance, it);
            ITreeAppendable _append = it.append("();");
            _append.newLine();
          }
        };
        this._jvmTypesBuilder.setBody(method, _function_1);
      } else {
        if (this.checkDiagnosticInCompiler) {
          XExpression _expr_1 = instance.getExpr();
          this._jvmTypesBuilder.setBody(method, _expr_1);
        } else {
          final Procedure1<ITreeAppendable> _function_2 = new Procedure1<ITreeAppendable>() {
            public void apply(final ITreeAppendable it) {
              XExpression _expr = instance.getExpr();
              boolean _hasDiagnostic = JexTestJvmModelInferrer.this._util.hasDiagnostic(_expr);
              if (_hasDiagnostic) {
                XExpression _expr_1 = instance.getExpr();
                JexTestJvmModelInferrer.this._util.generateUnsupportedOperationException(_expr_1, it);
              } else {
                XExpression _expr_2 = instance.getExpr();
                JexTestJvmModelInferrer.this._xbaseCompiler.toJavaExpression(_expr_2, it);
                it.append("return ");
                XExpression _expr_3 = instance.getExpr();
                JexTestJvmModelInferrer.this._xbaseCompiler.toJavaExpression(_expr_3, it);
                ITreeAppendable _append = it.append(";");
                _append.newLine();
              }
            }
          };
          this._jvmTypesBuilder.setBody(method, _function_2);
        }
      }
      _xblockexpression = (method);
    }
    return _xblockexpression;
  }
  
  public ITreeAppendable generateSetUpMethodBody(final JexTestCase testCase, final ITreeAppendable appendable) {
    ITreeAppendable _xifexpression = null;
    boolean _isDefaultInstanceTest = this._util.isDefaultInstanceTest(testCase);
    if (_isDefaultInstanceTest) {
      ITreeAppendable _xblockexpression = null;
      {
        StringConcatenation _builder = new StringConcatenation();
        String _defaultInstanceName = this._util.defaultInstanceName(testCase);
        _builder.append(_defaultInstanceName, "");
        _builder.append(" = new ");
        appendable.append(_builder);
        JvmParameterizedTypeReference _defaultInstanceType = this._util.defaultInstanceType(testCase);
        this._typeReferenceSerializer.serialize(_defaultInstanceType, testCase, appendable);
        ITreeAppendable _append = appendable.append("();");
        ITreeAppendable _newLine = _append.newLine();
        _xblockexpression = (_newLine);
      }
      _xifexpression = _xblockexpression;
    } else {
      EList<Instance> _instances = testCase.getInstances();
      for (final Instance instance : _instances) {
        StringConcatenation _builder = new StringConcatenation();
        String _name = instance.getName();
        _builder.append(_name, "");
        _builder.append(" = _init_");
        String _name_1 = instance.getName();
        _builder.append(_name_1, "");
        _builder.append("();");
        ITreeAppendable _append = appendable.append(_builder);
        _append.newLine();
      }
    }
    return _xifexpression;
  }
  
  public void inferStateTestMethods(final StateTestContext stateTestContext, final State state, final JvmGenericType jvmClass) {
    EList<JvmMember> _members = jvmClass.getMembers();
    final Procedure1<ITreeAppendable> _function = new Procedure1<ITreeAppendable>() {
      public void apply(final ITreeAppendable it) {
        final ITreeAppendable appendable = it;
        EList<ObjectTest> _objectTests = state.getObjectTests();
        final Procedure1<ObjectTest> _function = new Procedure1<ObjectTest>() {
          public void apply(final ObjectTest it) {
            XBlockExpression _test = it.getTest();
            JexTestJvmModelInferrer.this.generateTestHelperMethodCall("_test_", _test, appendable);
          }
        };
        IterableExtensions.<ObjectTest>forEach(_objectTests, _function);
      }
    };
    JvmOperation _inferTestHelperMethod = this.inferTestHelperMethod(stateTestContext, "_test_", state, _function);
    this._jvmTypesBuilder.<JvmOperation>operator_add(_members, _inferTestHelperMethod);
    EList<ObjectTest> _objectTests = state.getObjectTests();
    for (final ObjectTest objectTest : _objectTests) {
      XBlockExpression _test = objectTest.getTest();
      this.inferPropertiesTestMethods(((PropertiesTest) _test), jvmClass);
    }
  }
  
  public JvmOperation inferActionMethod(final StateTestContext stateTestContext, final TransitionExpressionAction action) {
    JvmOperation _xblockexpression = null;
    {
      String _relativeName = this._util.<JexTestSequence>relativeName(action, JexTestSequence.class);
      String _plus = ("_transition_exprAction_" + _relativeName);
      JvmTypeReference _newTypeRef = this._jvmTypesBuilder.newTypeRef(action, void.class);
      final JvmOperation method = this.inferTestHelperMethod(stateTestContext, _plus, _newTypeRef, action);
      XExpression _expr = action.getExpr();
      this._iJvmModelAssociator.associateLogicalContainer(_expr, method);
      final Procedure1<ITreeAppendable> _function = new Procedure1<ITreeAppendable>() {
        public void apply(final ITreeAppendable it) {
          XExpression _expr = action.getExpr();
          JexTestJvmModelInferrer.this.generateAssertCall(action, _expr, it, true);
        }
      };
      this._jvmTypesBuilder.setBody(method, _function);
      _xblockexpression = (method);
    }
    return _xblockexpression;
  }
  
  public JvmOperation inferTestHelperMethod(final StateTestContext innerStateTestContext, final String name, final JvmTypeReference type, final EObject context) {
    final Procedure1<JvmOperation> _function = new Procedure1<JvmOperation>() {
      public void apply(final JvmOperation it) {
        it.setVisibility(JvmVisibility.PRIVATE);
        final JvmParameterizedTypeReference instanceType = JexTestJvmModelInferrer.this._util.jvmInstanceType(context);
        boolean _notEquals = (!Objects.equal(instanceType, null));
        if (_notEquals) {
          EList<JvmFormalParameter> _parameters = it.getParameters();
          String _string = XbaseScopeProvider.IT.toString();
          JvmFormalParameter _parameter = JexTestJvmModelInferrer.this._jvmTypesBuilder.toParameter(context, _string, instanceType);
          JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
        }
        StateTestContext stateTestContext = innerStateTestContext;
        if ((stateTestContext instanceof StateFunction)) {
          EList<Parameter> _parameters_1 = ((StateFunction) stateTestContext).getParameters();
          for (final Parameter parameter : _parameters_1) {
            EList<JvmFormalParameter> _parameters_2 = it.getParameters();
            String _name = parameter.getName();
            JvmParameterizedTypeReference _type = parameter.getType();
            JvmFormalParameter _parameter_1 = JexTestJvmModelInferrer.this._jvmTypesBuilder.toParameter(parameter, _name, _type);
            JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_1);
          }
          StateTestContext _ancestor = JexTestJvmModelInferrer.this._util.<StateTestContext>ancestor(stateTestContext, StateTestContext.class);
          stateTestContext = _ancestor;
        }
        if ((stateTestContext instanceof JexTestSequence)) {
          EList<Instance> _instances = ((JexTestSequence) stateTestContext).getInstances();
          for (final Instance instance : _instances) {
            EList<JvmFormalParameter> _parameters_3 = it.getParameters();
            String _name_1 = instance.getName();
            JvmParameterizedTypeReference _jvmType = JexTestJvmModelInferrer.this._util.jvmType(instance);
            JvmFormalParameter _parameter_2 = JexTestJvmModelInferrer.this._jvmTypesBuilder.toParameter(instance, _name_1, _jvmType);
            JexTestJvmModelInferrer.this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters_3, _parameter_2);
          }
        }
      }
    };
    JvmOperation _method = this._jvmTypesBuilder.toMethod(context, name, type, _function);
    return _method;
  }
  
  public JvmOperation inferTestHelperMethod(final StateTestContext stateTestContext, final String prefix, final EObject context, final XExpression expr, final Procedure1<? super ITreeAppendable> generator) {
    JvmOperation _xblockexpression = null;
    {
      String _relativeName = this._util.<JexTestSequence>relativeName(context, JexTestSequence.class);
      String _plus = (prefix + _relativeName);
      final JvmOperation method = this.inferTestHelperMethod(stateTestContext, _plus, null, context);
      boolean _notEquals = (!Objects.equal(expr, null));
      if (_notEquals) {
        boolean _notEquals_1 = (!Objects.equal(generator, null));
        if (_notEquals_1) {
          this._iJvmModelAssociator.associateLogicalContainer(expr, method);
          this._jvmTypesBuilder.setBody(method, (Procedure1<ITreeAppendable>)generator);
        } else {
          this._jvmTypesBuilder.setBody(method, expr);
        }
      }
      _xblockexpression = (method);
    }
    return _xblockexpression;
  }
  
  public JvmOperation inferTestHelperMethod(final StateTestContext stateTestContext, final String prefix, final EObject context, final Procedure1<? super ITreeAppendable> initializer) {
    JvmOperation _xblockexpression = null;
    {
      String _relativeName = this._util.<JexTestSequence>relativeName(context, JexTestSequence.class);
      String _plus = (prefix + _relativeName);
      JvmTypeReference _newTypeRef = this._jvmTypesBuilder.newTypeRef(context, void.class);
      final JvmOperation method = this.inferTestHelperMethod(stateTestContext, _plus, _newTypeRef, context);
      final Procedure1<ITreeAppendable> _function = new Procedure1<ITreeAppendable>() {
        public void apply(final ITreeAppendable it) {
          initializer.apply(it);
        }
      };
      this._jvmTypesBuilder.setBody(method, _function);
      _xblockexpression = (method);
    }
    return _xblockexpression;
  }
  
  public ITreeAppendable generateTestHelperMethodCall(final String prefix, final EObject eObject, final ITreeAppendable appendable, final boolean asStatement, final boolean newline) {
    ITreeAppendable _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(prefix, "");
      String _relativeName = this._util.<StateTestContext>relativeName(eObject, StateTestContext.class);
      _builder.append(_relativeName, "");
      _builder.append("(");
      appendable.append(_builder);
      final String instanceName = this._util.instanceName(eObject);
      String separator = "";
      boolean _notEquals = (!Objects.equal(instanceName, null));
      if (_notEquals) {
        appendable.append(instanceName);
        separator = ", ";
      }
      StateTestContext stateTestContext = this._util.<StateTestContext>ancestor(eObject, StateTestContext.class);
      if ((stateTestContext instanceof StateFunction)) {
        EList<Parameter> _parameters = ((StateFunction) stateTestContext).getParameters();
        final Function1<Parameter,String> _function = new Function1<Parameter,String>() {
          public String apply(final Parameter it) {
            String _name = it.getName();
            return _name;
          }
        };
        String _join = IterableExtensions.<Parameter>join(_parameters, separator, ", ", "", _function);
        appendable.append(_join);
        StateTestContext _ancestor = this._util.<StateTestContext>ancestor(stateTestContext, StateTestContext.class);
        stateTestContext = _ancestor;
      }
      if ((stateTestContext instanceof JexTestSequence)) {
        EList<Instance> _instances = ((JexTestSequence) stateTestContext).getInstances();
        final Function1<Instance,String> _function_1 = new Function1<Instance,String>() {
          public String apply(final Instance it) {
            String _name = it.getName();
            return _name;
          }
        };
        String _join_1 = IterableExtensions.<Instance>join(_instances, separator, ", ", "", _function_1);
        appendable.append(_join_1);
      }
      appendable.append(")");
      if (asStatement) {
        appendable.append(";");
      }
      ITreeAppendable _xifexpression = null;
      if (newline) {
        ITreeAppendable _newLine = appendable.newLine();
        _xifexpression = _newLine;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public ITreeAppendable generateTestHelperMethodCall(final String prefix, final EObject eObject, final ITreeAppendable appendable) {
    ITreeAppendable _generateTestHelperMethodCall = this.generateTestHelperMethodCall(prefix, eObject, appendable, true, true);
    return _generateTestHelperMethodCall;
  }
  
  public ITreeAppendable generateLocalInstance(final Instance instance, final ITreeAppendable appendable) {
    ITreeAppendable _xblockexpression = null;
    {
      final JvmParameterizedTypeReference instanceType = this._util.jvmType(instance);
      this._typeReferenceSerializer.serialize(instanceType, instance, appendable);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(" ");
      String _name = instance.getName();
      _builder.append(_name, " ");
      _builder.append(" = _init_");
      String _relativeName = this._util.<JexTestSequence>relativeName(instance, JexTestSequence.class);
      _builder.append(_relativeName, " ");
      _builder.append("();");
      appendable.append(_builder);
      ITreeAppendable _newLine = appendable.newLine();
      _xblockexpression = (_newLine);
    }
    return _xblockexpression;
  }
  
  public ITreeAppendable generateStateTesterCall(final State state, final State stateRef, final ITreeAppendable appendable) {
    ITreeAppendable _xblockexpression = null;
    {
      State theState = state;
      boolean _equals = Objects.equal(theState, null);
      if (_equals) {
        theState = stateRef;
      }
      ITreeAppendable _xifexpression = null;
      boolean _notEquals = (!Objects.equal(theState, null));
      if (_notEquals) {
        ITreeAppendable _generateTestHelperMethodCall = this.generateTestHelperMethodCall("_test_", theState, appendable);
        _xifexpression = _generateTestHelperMethodCall;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public void generateTransition(final Transition transition, final ITreeAppendable appendable) {
    TransitionSource _source = transition.getSource();
    boolean _notEquals = (!Objects.equal(_source, null));
    if (_notEquals) {
      TransitionSource _source_1 = transition.getSource();
      State _state = _source_1.getState();
      TransitionSource _source_2 = transition.getSource();
      State _stateRef = _source_2.getStateRef();
      this.generateStateTesterCall(_state, _stateRef, appendable);
    }
    TransitionEffect _effect = transition.getEffect();
    this.generateTransitionActionsEffect(_effect, transition, appendable);
  }
  
  protected void _generateTransitionActionsEffect(final TransitionEffect effect, final Transition transition, final ITreeAppendable appendable) {
    EList<TransitionAction> _actions = transition.getActions();
    for (final TransitionAction action : _actions) {
      this.generateTransitionAction(action, transition, appendable);
    }
  }
  
  protected void _generateTransitionActionsEffect(final TransitionExceptionEffect effect, final Transition transition, final ITreeAppendable appendable) {
    TransitionEffect _effect = transition.getEffect();
    if ((_effect instanceof TransitionExceptionEffect)) {
      ITreeAppendable _append = appendable.append("try {");
      ITreeAppendable _increaseIndentation = _append.increaseIndentation();
      _increaseIndentation.newLine();
    }
    this._generateTransitionActionsEffect(((TransitionEffect) effect), transition, appendable);
    TransitionEffect _effect_1 = transition.getEffect();
    if ((_effect_1 instanceof TransitionExceptionEffect)) {
      TransitionEffect _effect_2 = transition.getEffect();
      final JvmParameterizedTypeReference exceptionClass = ((TransitionExceptionEffect) _effect_2).getExceptionClass();
      String _qualifiedName = exceptionClass.getQualifiedName();
      final String exceptionClassName = this._util.removeJavaLang(_qualifiedName);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(exceptionClassName, "");
      _builder.append(" should be thrown after ");
      EList<TransitionAction> _actions = transition.getActions();
      String _asSourceText = this._util.asSourceText(_actions, ", ");
      _builder.append(_asSourceText, "");
      final String message = _builder.toString();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("fail(\"");
      String _quote = this._util.quote(message, "\"");
      _builder_1.append(_quote, "");
      _builder_1.append("\");");
      appendable.append(_builder_1);
      ITreeAppendable _decreaseIndentation = appendable.decreaseIndentation();
      ITreeAppendable _newLine = _decreaseIndentation.newLine();
      _newLine.append("}");
      String exceptionVar = appendable.declareSyntheticVariable(transition, "e");
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append(" ");
      _builder_2.append("catch (Exception ");
      _builder_2.append(exceptionVar, " ");
      _builder_2.append(") {");
      ITreeAppendable _append_1 = appendable.append(_builder_2);
      ITreeAppendable _increaseIndentation_1 = _append_1.increaseIndentation();
      _increaseIndentation_1.newLine();
      StringConcatenation _builder_3 = new StringConcatenation();
      _builder_3.append("assertTrue(\"");
      String _quote_1 = this._util.quote(message, "\"");
      _builder_3.append(_quote_1, "");
      _builder_3.append("\", ");
      _builder_3.append(exceptionVar, "");
      _builder_3.append(" instanceof ");
      _builder_3.append(exceptionClassName, "");
      _builder_3.append(");");
      appendable.append(_builder_3);
      ITreeAppendable _decreaseIndentation_1 = appendable.decreaseIndentation();
      ITreeAppendable _newLine_1 = _decreaseIndentation_1.newLine();
      ITreeAppendable _append_2 = _newLine_1.append("}");
      _append_2.newLine();
    }
  }
  
  protected void _generateTransitionActionsEffect(final TransitionTargetEffect effect, final Transition transition, final ITreeAppendable appendable) {
    this._generateTransitionActionsEffect(((TransitionEffect) effect), transition, appendable);
    State _state = effect.getState();
    State _stateRef = effect.getStateRef();
    this.generateStateTesterCall(_state, _stateRef, appendable);
  }
  
  protected void _generateTransitionAction(final TransitionAction action, final Transition transition, final ITreeAppendable appendable) {
  }
  
  protected void _generateTransitionAction(final TransitionExpressionAction action, final Transition transition, final ITreeAppendable appendable) {
    String _xifexpression = null;
    XExpression _times = action.getTimes();
    boolean _notEquals = (!Objects.equal(_times, null));
    if (_notEquals) {
      XExpression _times_1 = action.getTimes();
      String _declareSyntheticVariable = appendable.declareSyntheticVariable(_times_1, "times");
      _xifexpression = _declareSyntheticVariable;
    }
    String timesVar = _xifexpression;
    boolean _notEquals_1 = (!Objects.equal(timesVar, null));
    if (_notEquals_1) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("for (int ");
      _builder.append(timesVar, "");
      _builder.append(" = ");
      appendable.append(_builder);
      this.generateTestHelperMethodCall("_transition_exprAction_times_", action, appendable, false, false);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("; ");
      _builder_1.append(timesVar, "");
      _builder_1.append(" > 0; ");
      _builder_1.append(timesVar, "");
      _builder_1.append("--) {");
      ITreeAppendable _append = appendable.append(_builder_1);
      ITreeAppendable _increaseIndentation = _append.increaseIndentation();
      _increaseIndentation.newLine();
    }
    this.generateTestHelperMethodCall("_transition_exprAction_", action, appendable, true, false);
    boolean _notEquals_2 = (!Objects.equal(timesVar, null));
    if (_notEquals_2) {
      ITreeAppendable _decreaseIndentation = appendable.decreaseIndentation();
      ITreeAppendable _newLine = _decreaseIndentation.newLine();
      _newLine.append("}");
    }
    appendable.newLine();
  }
  
  public void inferPropertiesTestMethods(final PropertiesTest pt, final JvmGenericType jvmClass) {
    final JvmTypeReference returnType = this._jvmTypesBuilder.newTypeRef(pt, void.class);
    StateTestContext _ancestor = this._util.<StateTestContext>ancestor(pt, StateTestContext.class);
    String _relativeName = this._util.<StateTestContext>relativeName(pt, StateTestContext.class);
    String _plus = ("_test_" + _relativeName);
    final JvmOperation propertiesMethod = this.inferTestHelperMethod(_ancestor, _plus, returnType, ((EObject) pt));
    this._iJvmModelAssociator.associateLogicalContainer(pt, propertiesMethod);
    final Procedure1<ITreeAppendable> _function = new Procedure1<ITreeAppendable>() {
      public void apply(final ITreeAppendable it) {
        EList<XExpression> _expressions = pt.getExpressions();
        for (final XExpression expr : _expressions) {
          JexTestJvmModelInferrer.this.generateAssertCall(pt, expr, it, false);
        }
      }
    };
    this._jvmTypesBuilder.setBody(propertiesMethod, _function);
    EList<JvmMember> _members = jvmClass.getMembers();
    this._jvmTypesBuilder.<JvmOperation>operator_add(_members, propertiesMethod);
  }
  
  public void generateAssertCall(final EObject owner, final XExpression expr, final ITreeAppendable appendable, final boolean explicitBoolean) {
    List<XExpression> exprs = Collections.<XExpression>unmodifiableList(Lists.<XExpression>newArrayList(expr));
    String assertMethodName = null;
    JvmTypeReference _elvis = null;
    JvmTypeReference _jvmType = this._util.jvmType(expr, owner);
    if (_jvmType != null) {
      _elvis = _jvmType;
    } else {
      JvmTypeReference _newTypeRef = this._jvmTypesBuilder.newTypeRef(owner, void.class);
      _elvis = ObjectExtensions.<JvmTypeReference>operator_elvis(_jvmType, _newTypeRef);
    }
    final JvmTypeReference type = _elvis;
    String _qualifiedName = type.getQualifiedName();
    final boolean isVoid = Objects.equal(_qualifiedName, "void");
    String _qualifiedName_1 = type.getQualifiedName();
    final boolean isLogical = Objects.equal(_qualifiedName_1, "boolean");
    boolean _or = false;
    if ((expr instanceof XUnaryOperation)) {
      _or = true;
    } else {
      _or = ((expr instanceof XUnaryOperation) || (expr instanceof XBinaryOperation));
    }
    final boolean isOperator = _or;
    boolean _or_1 = false;
    boolean _not = (!explicitBoolean);
    if (_not) {
      _or_1 = true;
    } else {
      boolean _and = false;
      if (!isOperator) {
        _and = false;
      } else {
        _and = (isOperator && isLogical);
      }
      _or_1 = (_not || _and);
    }
    if (_or_1) {
      String _xifexpression = null;
      if (isLogical) {
        _xifexpression = "assertTrue";
      } else {
        String _xifexpression_1 = null;
        boolean _not_1 = (!isVoid);
        if (_not_1) {
          _xifexpression_1 = "assertNotNull";
        }
        _xifexpression = _xifexpression_1;
      }
      assertMethodName = _xifexpression;
      boolean _and_1 = false;
      if (!(expr instanceof XBinaryOperation)) {
        _and_1 = false;
      } else {
        JvmIdentifiableElement _feature = ((XBinaryOperation) expr).getFeature();
        _and_1 = ((expr instanceof XBinaryOperation) && (_feature instanceof JvmOperation));
      }
      if (_and_1) {
        final XBinaryOperation binOp = ((XBinaryOperation) expr);
        JvmIdentifiableElement _feature_1 = binOp.getFeature();
        final JvmOperation feature = ((JvmOperation) _feature_1);
        JvmDeclaredType _declaringType = feature.getDeclaringType();
        final String typeName = _declaringType.getQualifiedName();
        boolean _and_2 = false;
        boolean _startsWith = typeName.startsWith("org.eclipse.xtext.xbase.lib.");
        if (!_startsWith) {
          _and_2 = false;
        } else {
          boolean _endsWith = typeName.endsWith("Extensions");
          _and_2 = (_startsWith && _endsWith);
        }
        if (_and_2) {
          String _simpleName = feature.getSimpleName();
          final String _switchValue = _simpleName;
          boolean _matched = false;
          if (!_matched) {
            if (Objects.equal(_switchValue,"operator_equals")) {
              _matched=true;
              assertMethodName = "assertEquals";
              XExpression _rightOperand = binOp.getRightOperand();
              XExpression _leftOperand = binOp.getLeftOperand();
              exprs = Collections.<XExpression>unmodifiableList(Lists.<XExpression>newArrayList(_rightOperand, _leftOperand));
            }
          }
          if (!_matched) {
            if (Objects.equal(_switchValue,"operator_tripleEquals")) {
              _matched=true;
              assertMethodName = "assertEquals";
              XExpression _rightOperand_1 = binOp.getRightOperand();
              XExpression _leftOperand_1 = binOp.getLeftOperand();
              exprs = Collections.<XExpression>unmodifiableList(Lists.<XExpression>newArrayList(_rightOperand_1, _leftOperand_1));
            }
          }
        }
      }
    }
    try {
      if (isVoid) {
        ITreeAppendable _append = appendable.append("try {");
        ITreeAppendable _increaseIndentation = _append.increaseIndentation();
        _increaseIndentation.newLine();
      }
      for (final XExpression subExpr : exprs) {
        boolean _or_2 = false;
        boolean _notEquals = (!Objects.equal(subExpr, expr));
        if (_notEquals) {
          _or_2 = true;
        } else {
          boolean _not_2 = (!isVoid);
          _or_2 = (_notEquals || _not_2);
        }
        this._xbaseCompiler.toJavaStatement(subExpr, appendable, _or_2);
      }
      appendable.newLine();
      StringConcatenation _builder = new StringConcatenation();
      String _asSourceText = this._util.asSourceText(expr);
      _builder.append(_asSourceText, "");
      _builder.append(" failed");
      String message = _builder.toString();
      boolean _not_3 = (!(owner instanceof TransitionAction));
      if (_not_3) {
        final Transition transition = this._util.<Transition>ancestor(expr, Transition.class);
        boolean _and_3 = false;
        boolean _notEquals_1 = (!Objects.equal(transition, null));
        if (!_notEquals_1) {
          _and_3 = false;
        } else {
          EList<TransitionAction> _actions = transition.getActions();
          boolean _isEmpty = _actions.isEmpty();
          boolean _not_4 = (!_isEmpty);
          _and_3 = (_notEquals_1 && _not_4);
        }
        if (_and_3) {
          String _plus = (message + " after ");
          EList<TransitionAction> _actions_1 = transition.getActions();
          String _asSourceText_1 = this._util.asSourceText(_actions_1, " ,");
          String _plus_1 = (_plus + _asSourceText_1);
          message = _plus_1;
        }
      }
      if (isVoid) {
        final String errorVar = appendable.declareSyntheticVariable(owner, "error");
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("} catch (junit.framework.AssertionFailedError ");
        _builder_1.append(errorVar, "");
        _builder_1.append(") {");
        ITreeAppendable _append_1 = appendable.append(_builder_1);
        _append_1.newLine();
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("fail(\"");
        String _quote = this._util.quote(message, "\"");
        _builder_2.append(_quote, "");
        _builder_2.append(": \" + ");
        _builder_2.append(errorVar, "");
        _builder_2.append(".getMessage());");
        ITreeAppendable _append_2 = appendable.append(_builder_2);
        ITreeAppendable _decreaseIndentation = _append_2.decreaseIndentation();
        _decreaseIndentation.newLine();
        ITreeAppendable _append_3 = appendable.append("}");
        _append_3.newLine();
      } else {
        boolean _notEquals_2 = (!Objects.equal(assertMethodName, null));
        if (_notEquals_2) {
          StringConcatenation _builder_3 = new StringConcatenation();
          _builder_3.append(assertMethodName, "");
          _builder_3.append("(\"");
          String _quote_1 = this._util.quote(message, "\"");
          _builder_3.append(_quote_1, "");
          _builder_3.append("\"");
          appendable.append(_builder_3);
          for (final XExpression subExpr_1 : exprs) {
            {
              appendable.append(", ");
              this._xbaseCompiler.toJavaExpression(subExpr_1, appendable);
            }
          }
          ITreeAppendable _append_4 = appendable.append(");");
          _append_4.newLine();
        }
      }
    } catch (final Throwable _t) {
      if (_t instanceof RuntimeException) {
        final RuntimeException re = (RuntimeException)_t;
        this._util.generateUnsupportedOperationException(owner, appendable);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  public void infer(final EObject testCase, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    if (testCase instanceof JexTestCase) {
      _infer((JexTestCase)testCase, acceptor, isPreIndexingPhase);
      return;
    } else if (testCase != null) {
      _infer(testCase, acceptor, isPreIndexingPhase);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(testCase, acceptor, isPreIndexingPhase).toString());
    }
  }
  
  public void generateTransitionActionsEffect(final TransitionEffect effect, final Transition transition, final ITreeAppendable appendable) {
    if (effect instanceof TransitionExceptionEffect) {
      _generateTransitionActionsEffect((TransitionExceptionEffect)effect, transition, appendable);
      return;
    } else if (effect instanceof TransitionTargetEffect) {
      _generateTransitionActionsEffect((TransitionTargetEffect)effect, transition, appendable);
      return;
    } else if (effect != null) {
      _generateTransitionActionsEffect(effect, transition, appendable);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(effect, transition, appendable).toString());
    }
  }
  
  public void generateTransitionAction(final TransitionAction action, final Transition transition, final ITreeAppendable appendable) {
    if (action instanceof TransitionExpressionAction) {
      _generateTransitionAction((TransitionExpressionAction)action, transition, appendable);
      return;
    } else if (action != null) {
      _generateTransitionAction(action, transition, appendable);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(action, transition, appendable).toString());
    }
  }
}
