

//From RBerteig at https://stackoverflow.com/a/809465
/** A compile time assertion check.
 *
 *  Validate at compile time that the predicate is true without
 *  generating code. This can be used at any point in a source file
 *  where typedef is legal.
 *
 *  On success, compilation proceeds normally.
 *
 *  On failure, attempts to typedef an array type of negative size. The
 *  offending line will look like
 *      typedef assertion_failed_file_h_42[-1]
 *  where file is the content of the second parameter which should
 *  typically be related in some obvious way to the containing file
 *  name, 42 is the line number in the file on which the assertion
 *  appears, and -1 is the result of a calculation based on the
 *  predicate failing.
 *
 *  \param predicate The predicate to test. It must evaluate to
 *  something that can be coerced to a normal C boolean.
 *
 *  \param file A sequence of legal identifier characters that should
 *  uniquely identify the source file in which this condition appears.
 */
    
#ifndef C_STATIC_ASSERT_H
#define C_STATIC_ASSERT_H

#define C_STATIC_ASSERT(predicate, file) C_STATIC_ASSERT_IMPL(predicate,__LINE__,file)

#define C_STATIC_ASSERT_PASTE(a,b) a##b
#define C_STATIC_ASSERT_IMPL(predicate, line, file) \
    typedef char C_STATIC_ASSERT_PASTE(assertion_failed_##file##_,line)[2*!!(predicate)-1];

#endif //C_STATIC_ASSERT_H