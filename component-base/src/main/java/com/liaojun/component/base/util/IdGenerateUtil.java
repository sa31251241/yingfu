package com.liaojun.component.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ChamIt-001 on 2017/5/9.
 */
public class IdGenerateUtil {
    protected static final Logger LOG = LoggerFactory.getLogger(IdGenerateUtil.class);

    private long workerId;
    private long datacenterId;
    private long sequence = 0L;

    private long twepoch = 0L;

    private long workerIdBits = 10L;
    private long datacenterIdBits = 0L;
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private long sequenceBits = 12L;

    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long lastTimestamp = -1L;

    private final static IdGenerateUtil idGenerateUtil = new IdGenerateUtil(PropConfigPool.getLong("idGenerateUtil.workerId"));

    public static IdGenerateUtil getInstance() {
        return idGenerateUtil;
    }

    public IdGenerateUtil(Long workerId) {
        this(workerId == null?1:workerId, 0);
    }

    private IdGenerateUtil(long workerId, long datacenterId) {
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        LOG.info("worker starting. timestamp left shift {}, datacenter id bits {}, worker id bits {}, sequence bits {}, workerid {}", new Object[]{timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId});
    }

    public synchronized Long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            LOG.error("clock is moving backwards.  Rejecting requests until {}", lastTimestamp);
//            "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp
            throw new RuntimeException("SYS_ID_WORKER_CLOCK_BACKWARD");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

}
