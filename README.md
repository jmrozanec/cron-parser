cron-parser
===========

[![Build Status](https://travis-ci.org/RedHogs/cron-parser.png?branch=master)](https://travis-ci.org/RedHogs/cron-parser) [![Stories in Ready](https://badge.waffle.io/RedHogs/cron-parser.png?label=ready)](https://waffle.io/RedHogs/cron-parser) [![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/RedHogs/cron-parser/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

A Java library that converts cron expressions into human readable strings.
Translated to Java from https://github.com/bradyholt/cron-expression-descriptor.

Original Author & Credit: Brady Holt (http://www.geekytidbits.com)
License: MIT
From v3.0.0 onwards, the library follows the [Semantic Versioning convention](http://semver.org/)

**Features**

 * Supports all cron expression special characters including * / , - ? L W, #.
 * Supports 5 or 6 (w/ seconds) part cron expressions.  Does NOT support Year in cron expression.
 * Provides casing options (Sentence, Title, Lower, etc.).
 * Support for non-standard non-zero-based week day numbers.
 * Supports printing to locale specific human readable format (Italian, English, Spanish and Dutch so far...).

**Download**

cron-parser is available in the [maven central repository](http://search.maven.org/#browse|987144470), please select the latest version from there.

**Usage Examples**

For complete examples, please view our tests: src/test/java/com/gr/cronparser/CronExpressionDescriptorTest.java

***Quick example***

    CronExpressionDescriptor descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(ENGLISH)
                        .forCronType(CronType.QUARTZ)
                        .instance();

                        String description = descriptor.getDescription("*/45 * * * * *");
                        //description will be: "Every 45 seconds"

                        description = descriptor.getDescription("5-10 * * * * *");
                        //description will be: "Seconds 5 through 10 past the minute"


                descriptor = DescriptorParamsBuilder.createDescriptor()
                        .withLocale(ENGLISH)
                        .forCronType(CronType.UNIX)
                        .instance();

                        description = descriptor.getDescription("0 23 ? * MON-FRI");
                        //description will be: "At 11:00:00 PM, Monday through Friday"

***New API***

    //define your own parser: arbitrary fields are allowed and last field can be optional
    CronParser parser = ParserDefinitionBuilder.defineParser()
                    .withSeconds()
                    .withMinutes()
                    .withHours()
                    .withDayOfMonth()
                    .withMonth()
                    .withDayOfWeek()
                    .withYear()
                    .andLastFieldOptional()
                    .instance();

    //or get a predefined instance
    parser = CronParserRegistry.instance().retrieveParser(QUARTZ);

    //create a descriptor for a specific Locale
    CronDescriptor descriptor = CronDescriptor.instance(Locale.UK);

    //parse some expression and ask descriptor for description
    descriptor.describe(parser.parse("*/45 * * * * *"));
    //description will be: "Every 45 seconds"
