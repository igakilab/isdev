outputdir="/usr/local/java11_compact"
rm -fr ${outputdir}
jlink \
    --vm=all \
    --output ${outputdir} \
    --module-path build/linux-arm-normal-clientANDserverANDminimal-release/images/jdk/jmods \
    --strip-debug \
    --add-modules java.base,java.logging,java.scripting,java.rmi,java.sql,java.xml,java.compiler,java.instrument,java.management,java.naming,java.prefs,java.security.jgss,java.security.sasl,java.sql.rowset,java.xml.crypto,jdk.compiler,java.desktop,jdk.jartool,jdk.javadoc,jdk.jconsole,jdk.jshell,jdk.jsobject,jdk.jlink,jdk.jdeps,jdk.scripting.nashorn.shell  \
    --no-header-files \
    --no-man-pages
