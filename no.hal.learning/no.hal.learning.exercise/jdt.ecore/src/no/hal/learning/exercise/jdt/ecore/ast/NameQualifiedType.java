/**
 */
package no.hal.learning.exercise.jdt.ecore.ast;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Name Qualified Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.learning.exercise.jdt.ecore.ast.NameQualifiedType#getQualifier <em>Qualifier</em>}</li>
 *   <li>{@link no.hal.learning.exercise.jdt.ecore.ast.NameQualifiedType#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link no.hal.learning.exercise.jdt.ecore.ast.NameQualifiedType#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see no.hal.learning.exercise.jdt.ecore.ast.AstPackage#getNameQualifiedType()
 * @model
 * @generated
 */
public interface NameQualifiedType extends AnnotatableType {
	/**
	 * Returns the value of the '<em><b>Qualifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualifier</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualifier</em>' containment reference.
	 * @see #setQualifier(Name)
	 * @see no.hal.learning.exercise.jdt.ecore.ast.AstPackage#getNameQualifiedType_Qualifier()
	 * @model containment="true"
	 * @generated
	 */
	Name getQualifier();

	/**
	 * Sets the value of the '{@link no.hal.learning.exercise.jdt.ecore.ast.NameQualifiedType#getQualifier <em>Qualifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualifier</em>' containment reference.
	 * @see #getQualifier()
	 * @generated
	 */
	void setQualifier(Name value);

	/**
	 * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
	 * The list contents are of type {@link no.hal.learning.exercise.jdt.ecore.ast.Annotation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Annotations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Annotations</em>' containment reference list.
	 * @see no.hal.learning.exercise.jdt.ecore.ast.AstPackage#getNameQualifiedType_Annotations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Annotation> getAnnotations();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' containment reference.
	 * @see #setName(SimpleName)
	 * @see no.hal.learning.exercise.jdt.ecore.ast.AstPackage#getNameQualifiedType_Name()
	 * @model containment="true"
	 * @generated
	 */
	SimpleName getName();

	/**
	 * Sets the value of the '{@link no.hal.learning.exercise.jdt.ecore.ast.NameQualifiedType#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(SimpleName value);

} // NameQualifiedType
