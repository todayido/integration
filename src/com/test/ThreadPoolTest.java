package com.test;

import java.io.Serializable;

public class ThreadPoolTest implements Runnable, Serializable {

        private static final long serialVersionUID = 0;
        private static int consumeTaskSleepTime = 1000;
        // 保存任务所需要的数据
        private Object threadPoolTaskData;

        ThreadPoolTest(Object tasks){
            this.threadPoolTaskData = tasks;
        }

        public void run(){
            try{
                // //便于观察，等待一段时间
                Thread.sleep(consumeTaskSleepTime);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            threadPoolTaskData = null;
        }

        public Object getTask(){
            return this.threadPoolTaskData;
        }
}
